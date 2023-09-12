package rockthejvm.virtualthread;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

// --enable-preview
public class VirtualThreads {

    static final Logger logger = LoggerFactory.getLogger(VirtualThreads.class);

    static void log(String message) {
        logger.info("{} | {}", Thread.currentThread(), message);
    }

    @SneakyThrows
    private static void sleep(Duration duration) {
        Thread.sleep(duration);
    }

    /*
        Akka actors, Scala Fibers, Kotlin coroutines in Java Virtual threads

        Every classic thread in the jvm is a wrapper for an OS thread = platform thread
        Platform threads are very expensive, heavyweight creation.
        Any thread or VT has a ThreadLocal witch is a space where only that thread can read and write and how we can have many VT is not a good idea to abuse with ThreadLocal
        Virtual threads
            - VT are cheap to create
            - VT stores pointer to the advance of an execution that can be yielded and resumed later
            - When a classic threads is blocked, also is blocking the platform thread
            - With VT a blocking it's only a semantic blocking, the VT is descheduled from the platform thread (carrier)
              and this will pick another VT. The JVM will reschedule the blocking VT once block ends. See pinning below
            - Platform forkjoinpool by default has:
                parallelism = to availableProcessors
                maxPoolSize = 256
                minRunnableValue = max(parallelism / 2, 1)
            - How Vts are very fast to create, ThreadPools has less sense
    */

    /*
        This function must take over a second to end, in theory if all starts at the same time and sleeps 1 second...
        but it takes much more bcs create a thread it's expensive
    */
    private static void oomExample() {
        for (int i = 0; i < 100_000; i++) {
            new Thread(() -> sleep(Duration.ofSeconds(1))).start();
        }
    }

    private static Thread virutalThread(String name, Runnable runnable) {
        return Thread.ofVirtual()
                .name(name)
                .start(runnable);
    }

    // morning routine (similar to kotlin tutorial)
    static Thread bathTime() {
        return virutalThread("BathTme", () -> {
            log("I'm going for a bath");
            sleep(Duration.ofMillis(500));
            log("I'm done with the bath");
        });
    }

    static Thread boilWater() {
        return virutalThread("BoilWater", () -> {
            log("I'm going to boil water");
            sleep(Duration.ofMillis(1000));
            log("I'm done with the water");
        });
    }

    @SneakyThrows
    static void concurrentMorningRoutine() {
        var bathTime = bathTime();
        var boilingWater = boilWater();
        bathTime.join();
        boilingWater.join();
    }

    // executor service on VTs
    @SneakyThrows
    static void concurrentMorningRoutineWithExecutors() {
        final ThreadFactory factory = Thread.ofVirtual().name("routine-", 0).factory();
        try (var executor = Executors.newThreadPerTaskExecutor(factory)) {
            var bathTime = executor.submit(() -> {
                log("I'm going for a bath");
                sleep(Duration.ofMillis(500));
                log("I'm done with the bath");
            });
            var boilingWater = executor.submit(() -> {
                log("I'm going to boil water");
                sleep(Duration.ofMillis(1000));
                log("I'm done with the water");
            });

            bathTime.get();
            boilingWater.get();
        }
    }

    static int numberOfCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    static void viewCarrierThreadPoolSize() {
        final ThreadFactory factory = Thread.ofVirtual().name("routine-", 0).factory();
        try (var executor = Executors.newThreadPerTaskExecutor(factory)) {
            IntStream.range(0, numberOfCores() + 1).forEach(i -> executor.submit(() -> {
                log("Hi from virtual thread " + i);
                sleep(Duration.ofSeconds(1));
            }));
        }
    }

    static boolean alwaysTrue() {
        return true;
    }

    static Thread workHard() {
        return virutalThread("WorkingHard", () -> {
            log("I'm working hard");
            while (alwaysTrue()) {
                // Thread.yield(); with this operation we can force to this thread to yield the carrier
            }
            sleep(Duration.ofMillis(100));
            log("I'm done with the work");
        });
    }

    static Thread waitHard() {
        return virutalThread("WaitingHard", () -> {
            log("I'm waiting hard");
            sleep(Duration.ofSeconds(20));
            log("I'm done with the waiting");
        });
    }

    static Thread takeABreak() {
        return virutalThread("TakingBreak", () -> {
            log("I'm going to take break");
            sleep(Duration.ofSeconds(1));
            log("Done with break");
        });
    }

    // Use VirtualThreads Only One Thread.run.xml Set 1 platform thread => -Djdk.virtualThreadScheduler.parallelism=1 -Djdk.virtualThreadScheduler.maxPoolSize=1 -Djdk.virtualThreadScheduler.minRunnable=1
    @SneakyThrows
    static void workHardRoutine() {
        var workingHard = workHard(); // starts the VT on the only carrier thread
        var takingBreak = takeABreak();
        workingHard.join(); //Blocking and never does take a break
        takingBreak.join();
    }

    /*
        With CPU-Bound tasks, virtual threads will NOT yield the control over the platform/carrier
        But instance of cpu task it will be a sleep in that case will force the VT to yield
    */

    // Use VirtualThreads Only One Thread.run.xml Set 1 platform thread => -Djdk.virtualThreadScheduler.parallelism=1 -Djdk.virtualThreadScheduler.maxPoolSize=1 -Djdk.virtualThreadScheduler.minRunnable=1
    @SneakyThrows
    static void waitingHardRoutine() {
        var waitingHard = waitHard(); // starts the VT on the only carrier thread
        var takingBreak = takeABreak();
        waitingHard.join();
        takingBreak.join();
    }

    // In this case yes the VT is being detached form the carrier

    /*
        Pinning yield not possible...
        When code it's being executing in a synchronized block or in a java native interface
    */

    // Example pinning one toilet in the office
    static class Bathroom {
        synchronized void use() { // This block pin the VT to the carrier til block completes
            log("Using the bathroom");
            sleep(Duration.ofSeconds(1)); // In a sleep normally yield but no here, we are in a synchronized block
            log("Done using the bathroom");
        }
    }

    static Bathroom bathroom = new Bathroom();

    static Thread goToTheBathroom() {
        return virutalThread("GoingToTheBathroom", () -> bathroom.use());
    }

    @SneakyThrows
    static void twoPeopleRacingToTheBathroom() {
        var riccardo = goToTheBathroom();
        var daniel = goToTheBathroom();
        riccardo.join();
        daniel.join();
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("jdk.virtualThreadScheduler.parallelism"));
        System.out.println(System.getProperty("jdk.virtualThreadScheduler.maxPoolSize"));
        System.out.println(System.getProperty("jdk.virtualThreadScheduler.minRunnable"));
        twoPeopleRacingToTheBathroom();
    }

}
