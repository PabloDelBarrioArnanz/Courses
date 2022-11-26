public class Lesson2AtomicOperations {

    /*
       1. Resources shared between threads
        - Vars
        - Files
        - Messages...

       2. Atomic operations
       An operation or a set of operations are considered atomic, if it appears to the rest of the system as if it occurred at once
       Single step "all or nothing"
       items++ and items-- aren't atomic operations
        - get current value
        - increment/decrement
        - set new value
       This 3 operations can be mixed between the sharing threads
    */

    public static void main(String[] args) throws InterruptedException {


        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        /*
        incrementingThread.start();
        incrementingThread.join();

        decrementingThread.start();
        decrementingThread.join();

        System.out.println("We currently have: " + inventoryCounter.getItems() + " items."); returns zero
        */
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
        private Integer items = 0;
        private static final Object o = new Object();

        public void increment() {
            items++;
        }

        public void decrement() {
            items--;
        }

        public int getItems() {
            return items;
        }
    }
}