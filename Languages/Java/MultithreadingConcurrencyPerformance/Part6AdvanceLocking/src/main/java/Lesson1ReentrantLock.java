public class Lesson1ReentrantLock {


    /*
        1. ReentrantLock
            - Works just like the synchronized keyword applied on an object
            - Requires explicit locking and unlocking
            - No need to create an unnecessary object to lock

                Example with synchronized
                Object lockObject = new Object();
                Resource resource = new Resource();

                public void method() {
                    synchronized(lockObject) {
                        //use resource
                    }
                }

                Example with reentrantLock
                Lock lockObject = new ReentrantLock(); //ReentrantLock(true) makes ReentrantLock fairness but may reduce throughput
                Resource resource = new Resource();

                public void method() {
                    lockObject.lock();
                    //use resource
                    lockObject.unlock();
                }
            - Problems exceptions thrown in lock object will cut the execution and never unlock would be executed (try-finally with unlock)
                Lock lockObject = new ReentrantLock(); //ReentrantLock(true) makes ReentrantLock fairness but may reduce throughput
                Resource resource = new Resource();

                public void method() {
                    lockObject.lock();
                    try {
                        //use resource
                    } finally {
                        lockObject.unlock();
                    }
                }

        2. ReentrantLock.tryLock()
            Returns true and acquires a lock if available
            Returns false and does not get suspended, if the lock is unavailable

            Example
            Lock lockObject = new ReentrantLock(); //ReentrantLock(true) makes ReentrantLock fairness but may reduce throughput
            Resource resource = new Resource();

            public void method() {
                if (lockObject.tryLock()) {
                    try {
                        //use resource
                    } finally {
                        lockObject.unlock();
                    }
                } else {
                    //will execute this if false, no get suspended
                }
            }


        3. ReentrantLock.lockInterruptibly()
            Allows us to define a behaviour for an interrupted thread
            Useful for watchdog methods


            Example
            Lock lockObject = new ReentrantLock(); //ReentrantLock(true) makes ReentrantLock fairness but may reduce throughput
            Resource resource = new Resource();

            public void method() {
                try {
                    lockObject.lockInterruptibly();
                } catch (InterruptedException e) {
                    cleanUpAndExit();
                }
                //use resource
                lockObject.unlock();
            }
    */
}