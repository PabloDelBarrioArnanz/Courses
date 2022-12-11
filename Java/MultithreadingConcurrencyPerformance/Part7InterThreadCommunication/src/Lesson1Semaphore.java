import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lesson1Semaphore {

    /*

        1. Semaphore Introduction.
           - Can be used to restrict the number of users to a resource or a group of resources
           - Unlike the locks allows that allows only one user per resource

            Semaphore semaphore = new Semaphore(NUMBER_OF_PERMITS);
            semaphore.acquire(); //NUMBER_OF_PERMITS - 1
            useResource()
            semaphore.release();

            If a thread executes semaphore.acquire() and there is no slots available then thread will be blocked

        2. Binary Semaphores (No reentrant).
           Semaphore initialized with 1 is not reentrant
           semaphore.acquire();
           semaphore.acquire(); //Blocked

        3. Semaphores VS Locks.
           A lock it's like a semaphore witch only has 1 slot available
           A semaphore it not a great choice for a lock
            - Semaphore doesn't have a notion of owner thread
            - Many threads can acquire a permit
            - The same thread can acquire the semaphore multiple times
            - Semaphore can be released by any thread even by a thread that hasn't actually acquired it

        4. Producer Consumer using Semaphore.
           Semaphore full = new Semaphore(0);
           Semaphore empty = new Semaphore(1);
           Item item = null;

           Consumer
           while (true) {
                full.acquire();
                consume(item);
                empty.release();
           }

           Producer
           while (true) {
                empty.acquire();
                item = produce();
                full.release();
           }

           This can not be realized with lock bcs a thread can't release a lock locked by another thread
           Can work only for 1 consume and 1 producer

           Producer Consumer with multiple consumers and producers
           int capacity = 5;
           Semaphore full = new Semaphore(3);
           Semaphore empty = new Semaphore(capacity); //storage capacity
           Queue<Double> queue = new LinkedList<>(); //storage for items
           ReadWriteLock lock = new ReentrantReadWriteLock();
           Lock readLock = lock.readLock();
           Lock writeLock = lock.writeLock();

           Consumer
           while (true) {
                full.acquire();
                readLock.lock();
                if (!queue.isEmpty()) {
                    System.out.println("Getting element to consume...");
                    Double item = queue.poll();
                } else {
                    System.out.println("Nothing"); //Here no problem
                }
                readLock.unlock();
                System.out.println("Consuming...");
                Thread.sleep(1000); //consuming...
                empty.release();
            }

           Producer
            while (true) {
                System.out.println("Producing...");
                Thread.sleep(1200); //producing...
                Double item = 1d;
                empty.acquire();
                writeLock.lock();
                if (capacity < queue.size()) { //Needed bcs one thread makes acquire and lock with only 1 slot free, then a consumer ends consume from a previous item and makes empty.release and when an other-producer tries to add a item storage is full
                    System.out.println("Setting element...");
                    queue.offer(item);
                } else {
                    System.out.println("Full"); //Error we are loosing items bcs can't be storage
                }
                writeLock.unlock();
                full.release();
            }

    */
    public static void main(String[] args) {
        new Test();
    }

    public static class Test {
        int capacity = 5;
        Semaphore full = new Semaphore(3);
        Semaphore empty = new Semaphore(capacity); //storage capacity
        Queue<Double> queue = new LinkedList<>(); //storage for items
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();

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
                full.acquire();
                readLock.lock();
                if (!queue.isEmpty()) {
                    System.out.println("Getting element to consume...");
                    Double item = queue.poll();
                } else {
                    System.out.println("Nothing");
                }
                readLock.unlock();
                System.out.println("Consuming...");
                Thread.sleep(1000); //consuming...
                empty.release();
            }
        }

        public void produce() throws InterruptedException {
            while (true) {
                System.out.println("Producing...");
                Thread.sleep(1200); //producing...
                Double item = 1d;
                empty.acquire();
                writeLock.lock();
                if (capacity < queue.size()) {
                    System.out.println("Setting element...");
                    queue.offer(item);
                } else {
                    System.out.println("Full"); //Error we are loosing items bcs can't be storage
                }
                writeLock.unlock();
                full.release();
            }
        }

    }

}