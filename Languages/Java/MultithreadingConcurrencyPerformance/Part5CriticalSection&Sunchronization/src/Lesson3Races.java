public class Lesson3Races {

    /*
        1. Race Condition.
        Condition when multiple threads are accessing a shared resource
        At least one is modifying the resource
        The core of the problem is non-atomic operations performed on the shared resource
        Solution
            1. Identify the critical section where the race condition is happening
            2. Protection of the critical section by a synchronized block

        2. Data Race (metrics example).
        Compiler and CPU may execute the instructions our ot order to optimize performance and utilization
        They will do so while maintain the logical correctness of the code (no dependency)
        Solution
            1. Synchronization of methods witch modify shared variables
            2. Declaration of shared variables with the volatile keyword

        Conclusion
        Synchronized - Solves both Race Condition and Data Race but has a performance penalty
        Volatile     - Solves Race Condition for read/write (long and doubles)
                     - Solves all Data Races by guaranteeing order
    */
}
