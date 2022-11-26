
public class Lesson1PerformanceAndOptimizingForLatency {

    /*
        1. Performance criteria definition - criteria to check/compare the performance of our app
        2. Performance in multithreaded applications
            - Latency    = time to completion of a task. Measured in time units.
            - Throughput = the amount of task completed in a given period. Measured in tasks/time unit
            Theoretical reduction of latency by N => Performance improvement by a factor of N.
                So N = ? How many subtask to break the original task? Plus be careful*
                In a general pc => N = numbers of cores

         Number of threads = number of cores only if threads are runnable and can run without interruption
         HyperThreading - Virtual Cores VS Physical Cores
     */

    /*
        * Break a task into multiples task has a penalty of time
         - task break
         - thread creating, passing task
         - time between start and thread getting scheduled
         - time until last thread finishs and signals
         - time of aggregation thread starts
         - aggregation time
         ===> Sometimes is slower use parallel programing (intersection in time)
    */
}
