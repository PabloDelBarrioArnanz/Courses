public class Lesson1Introduction {

    /*
        1. Problems and limitations of lock (synchronized, Reentrant...)
           Majority of multi-threaded programming is still done with locks
           Most of the concurrency problems are easier and safe to solve with locks
           Problems
            - Deadlocks are generally unrecoverable
            - The more locks, the higher the chances for a deadlock
            - All thread become as slow as the slowest thread
            - Two threads sharing a lock Low priority and High priority (low p can block high p)
            - Thread dies, gets interrupted or forgets to release a lock...

        2. Introduction ot lock free programming


        3. Review of atomic instruction
           - Read/Assignment on all primitive types (except for long and double)
           - Read/Assignment on all references
           - Read/Assignment on volatile long and double

        4. Introduction to a new group of atomic operations
           - java.util.concurrent.atomic package
           - Utilize platform specific implementation of operations

    */

}