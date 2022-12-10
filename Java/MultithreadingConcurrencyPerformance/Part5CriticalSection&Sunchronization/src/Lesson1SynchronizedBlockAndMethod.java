public class Lesson1SynchronizedBlockAndMethod {

    /*
        1. Critical Section.
        Sections where threads access to shared data are limited to only one thread

        2. Synchronized - Monitor/Lock
        Tools to create critical sections
            1. Synchronized Keyword
            public synchronized void ...
            - Locking mechanism
            - Used to restrict access to a critical section or entire method to a single thread at time
            If we got a class with 2 methods annotated with "synchronized" if a thread is executing one of them any other
            thread can't execute any method. Bcs synchronized it's applied per object
            2. Synchronized block
            synchronized(lockingObject) { ... }
             - 1. it's similar to synchronized(this)...
             - With this locking mechanism we can have separated critical sections
             - Not all method it's synchronized

        Synchronized block and method it's called Reentrant, in other words if a thread is accessing a synchronized method
        while already being in a different synchronized method or block, it will be able to access that synchronized method
        with no problem. Basically a thread cannot prevent itself entering a critical section.

    */
}