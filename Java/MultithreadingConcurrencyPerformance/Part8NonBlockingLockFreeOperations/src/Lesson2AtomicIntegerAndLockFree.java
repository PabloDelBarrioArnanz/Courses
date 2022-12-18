import java.util.concurrent.atomic.AtomicInteger;

public class Lesson2AtomicIntegerAndLockFree {

    /*
        Atomic Integer
        Pro
            - Simplicity
            - No need to for locks
            - No race conditions or data races
        Cons
            - Only the operation itself is atomic
            - There's still race conditions between 2 separate atomic operations
        - Locking free E-commerce inventory counter
    */

    static {
        int initialValue = 0;
        AtomicInteger atomicInteger = new AtomicInteger(initialValue);

        atomicInteger.decrementAndGet(); //returns the new value
        atomicInteger.getAndDecrement(); //return the previous value

        atomicInteger.addAndGet(5); //return the previous value
        atomicInteger.getAndAdd(5); //return the previous value
    }


    public static class InventoryCounterExample {

        public static void main(String[] args) throws InterruptedException {
            InventoryCounter inventoryCounter = new InventoryCounter();

            IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
            DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

            incrementingThread.start();
            decrementingThread.start();

            incrementingThread.join();
            decrementingThread.join();

            System.out.println("We currently have: " + inventoryCounter.getItems() + " items.");
        }

        public static class IncrementingThread extends Thread {
            private final InventoryCounter inventoryCounter;

            public IncrementingThread(InventoryCounter inventoryCounter) {
                this.inventoryCounter = inventoryCounter;
            }

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    inventoryCounter.increment();
                }
            }
        }

        public static class DecrementingThread extends Thread {
            private final InventoryCounter inventoryCounter;

            public DecrementingThread(InventoryCounter inventoryCounter) {
                this.inventoryCounter = inventoryCounter;
            }

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    inventoryCounter.decrement();
                }
            }
        }

        private static class InventoryCounter {
            private AtomicInteger items = new AtomicInteger(0);
            private static final Object o = new Object();

            public void increment() {
                items.incrementAndGet();
            }

            public void decrement() {
                items.decrementAndGet();
            }

            public int getItems() {
                return items.get();
            }
        }

    }

}
