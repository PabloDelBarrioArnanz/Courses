import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lesson2InterThreadCommunication {

    /*

        1. Review inter-thread communication
           - Thread.interrupt to send a signal to a thread and stops it
           - Thread.join to wait for a thread a being notified when finish
           - Semaphore release if a thread makes this and other thread was waiting for semaphore will be notified

        2. Semaphore as a particular case of condition variable
           Calling acquire on a semaphore it's like checking "is number of permits > 0" this is a condition variable

        3. Condition variables java.util.concurrent.locks.Condition
           Are conditions witch have to be ok before to continue, any thread will sleep till another thread set condition ok then every thread sleeping will be notified
           Condition variables are always associated with a lock witch ensures atomic check and modification of the shared var

           Lock lock = new ReentrantLock();
           Condition condition = lock.newCondition();

           await()  - locks till signalled
           signal() - wakes up a single thread waiting on the condition variable, a thread that wakes up has to reacquire the lock, if no one is waiting signal does nothing
           signalAll() - wakes up all thread waiting for the condition

           Producer Consumer with Condition

    */

    public static void main(String[] args) {
        new Test();
    }

    public static class Test {
        int capacity = 5;
        Queue<Double> queue = new LinkedList<>(); //storage for items
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        public Test() {
            new Thread(() -> {
                try {
                    consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            new Thread(() -> {
                try {
                    consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            new Thread(() -> {
                try {
                    consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            new Thread(() -> {
                try {
                    produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            new Thread(() -> {
                try {
                    produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            new Thread(() -> {
                try {
                    produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        public void consume() throws InterruptedException {
            while (true) {
                Double item = 1d;
                lock.lock();
                try {
                    while (queue.isEmpty())
                        condition.await();
                    item = queue.poll();
                } finally {
                    condition.signal();
                    lock.unlock();
                }
                System.out.println("Consuming...");
                Thread.sleep(1100);
            }
        }

        public void produce() throws InterruptedException {
            while (true) {
                System.out.println("Producing...");
                Thread.sleep(1200); //producing...
                Double item = 1d;
                lock.lock();
                try {
                    while (queue.size() == capacity)
                        condition.await();
                    queue.offer(item);
                } finally {
                    condition.signal();
                    lock.unlock();
                }
            }
        }

    }

}
