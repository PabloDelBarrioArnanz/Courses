
public class Lesson5PerformanceAndOptimizingForThroughput {

    /*
        Throughput = the amount of task completed in a given period. Measured in tasks/time unit
        Unrelated task can be executed in parallel

        Thread pooling => few thread already created much before to be used
        A thread pooling has a queue for task and as it's finish a task it will start another from the queue
        JDK comes with a few implementations of thread pools
            - Fixed Thread Pool => Executor executor = new FixedThreadPool(numberOfThreads)
     */
}
