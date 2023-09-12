import java.util.Random;

public class Lesson4DeadlocksAndAdvanceLocking {

    /*
        Locking Strategies
            - Fine-Grained Locking
              Multiples locks
              Possible deadlock (circular dependency)

            - Coarse-Grained Locking
              One big lock
              It's more simple but has a bigger performance penalty

        Deadlock
        Thread 1
        1lock(A)                          2lock(B)
           4!lock(B)                        3!lock(A)
                delete(A, item)                delete(B, item)
                add(B, item)                   add(A, item)
            unlock(A)                        unlock(A)
        unlock(B)                         unlock(B)

        Deadlock Conditions
        - Mutual Exclusion: only one thread can have exclusive access to a resource in a given moment
        - Hold and Wait: at least one thread is holding a resource and is waiting for another resource
        - Non-preemptive allocation: a resource is released only after the thread is done using it
        - Circular wait: a chain of at least two thread each one is holding one resource and waiting for other

        Deadlock Solutions
        - Avoid circular wait: enforce a strict order in lock acquisition
        - Easy with a small number of locks
        - Watchdog (deadlock detector) checks periodically the status of a particular register that to register needs to be updated by
          every thread, if the watchdog detects that this register hasn't been updated it knows the threads are not responsive
            - Thread interruption if deadlock (not possible with synchronized)
            - tryLock operations (not possible with synchronized)
    */

    public static void main(String[] args) {
        Intersection intersection = new Intersection();

        Thread trainAThread = new Thread(new TrainA(intersection));
        Thread trainBThread = new Thread(new TrainB(intersection));

        trainAThread.start();
        trainBThread.start();
    }

    public static class TrainA implements Runnable {
        private Intersection intersection;
        private final Random random = new Random();

        public TrainA(Intersection intersection) {
            this.intersection = intersection;
        }

        @Override
        public void run() {
            while (true) {
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                intersection.takeRoadA();
            }
        }
    }

    public static class TrainB implements Runnable {
        private final Intersection intersection;
        private final Random random = new Random();

        public TrainB(Intersection intersection) {
            this.intersection = intersection;
        }

        @Override
        public void run() {
            while (true) {
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                intersection.takeRoadB();
            }
        }
    }

    public static class Intersection {
        private final Object roadA = new Object();
        private final Object roadB = new Object();

        public void takeRoadA() {
            synchronized (roadA) {
                System.out.println("Road A locked by thread " + Thread.currentThread().getName());
                synchronized (roadB) {
                    System.out.println("Train is passing through road A");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        public void takeRoadB() {
            synchronized (roadB) {
                System.out.println("Road B locked by thread " + Thread.currentThread().getName());
                synchronized (roadA) {
                    System.out.println("Train is passing through road B");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
