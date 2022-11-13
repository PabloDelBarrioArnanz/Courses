public class Lesson5PerformanceAndOptimizingForThroughput {

    /*
        Throughput = the amount of task completed in a given period. Measured in tasks/time unit
        Unrelated task can be executed in parallel

        Thread pooling => few thread already created much before to be used
        A thread pooling has a queue for task and as it's finish a task it will start another from the queue
        JDK comes with a few implementations of thread pools
            - Fixed Thread Pool => Executor/ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads)
              This executor uses a queue of task and as a thread it's liberated a new task will be released from the queue to be executed

            - Cached Thread Pool => Executor/ExecutorService executor = Executors.newCachedThreadPool()
              This executor has no queue, if a task comes and there is no thread waiting a new thread will be created
              Can grow till Integer.MAX_VALUE of threads

            - Single Thread Executor => Executor/ExecutorService executor = Executors.newSingleThreadExecutor()
              Similar to newFixedThreadPool(1) but guaranteed not to be reconfigurable to use additional threads

            - Work Stealing Pool => Executor/ExecutorService executor = Executors.newWorkStealingPool(parallelism)
              Uses Fork/Join
              Creates a thread pool that maintains enough threads to support the given parallelism level
              May use multiple queues to reduce contention, a thread has a queue and start task from his queue if it's empty will find task form other thread's queue
              New subtask spawned from original task will be injected in these queues
              A workStealingPool makes no guarantees about the order in which submitted tasks are executed

            - Virtual Thread Per Task Executor => Executor/ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()
              Creates an Executor that starts a new virtual Thread for each task. The number of threads created by the Executor is unbounded.

            - Thread Per Task Executor => Executor/ExecutorService executor = Executors.newThreadPerTaskExecutor()
              Creates an Executor that starts a new Thread for each task. The number of threads created by the Executor is unbounded.


        Virtual Threads
        At a high level, a thread is managed and scheduled by the operating system, while a virtual thread is managed and scheduled by a virtual machine
        Now, to create a new kernel thread, we must do a system call, and that's a costly operation
        (That's why we're using thread pools instead of reallocating and deallocating threads as needed)
        VT's allocation doesn't require a system call, and they're free of the operating system's context switch
        VT's run on the carrier thread, which is the actual kernel thread used under-the-hood
        Key property of virtual threads is that they don't block our carrier thread
        The continuation can potentially block a carrier thread

        Structured Concurrency
        In this case if a future fails others will continue and block won't fail until all are complete
        Response fetch(Long id) throws ExecutionException, InterruptedException {
            Future<AccountDetails>  accountDetailsFuture  = es.submit(() -> getAccountDetails(id));
            Future<LinkedAccounts> linkedAccountsFuture = es.submit(() -> fetchLinkedAccounts(id));
            Future<DemographicData> userDetailsFuture = es.submit(() -> fetchUserDetails(id));

            AccountDetails accountDetails  = accountDetailsFuture.get();
            LinkedAccounts linkedAccounts  = linkedAccountsFuture.get();
            DemographicData userDetails    = userDetailsFuture.get();

            return new Response(accountDetails, linkedAccounts, userDetails);
        }

        Now if some future fail all others will be stopped and process will return error immediately
        Only success will be returned if all vt return success
        static {
            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

                Future<AccountDetails> accountDetailsFuture = scope.fork(() -> getAccountDetails(id)); //Virtual Thread
                Future<LinkedAccounts> linkedAccountsFuture = scope.fork(() -> fetchLinkedAccounts(id)); //Virtual Thread
                Future<DemographicData> userDetailsFuture = scope.fork(() -> fetchUserDetails(id)); //Virtual Thread

                scope.join(); // Join all subtasks
                scope.throwIfFailed(e -> new WebApplicationException(e));

                //The subtasks have completed by now so process the result
                return new Response(accountDetailsFuture.resultNow(),
                        linkedAccountsFuture.resultNow(),
                        userDetailsFuture.resultNow());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Too we can stop all process when one of them return success changing the scope
        StructuredTaskScope.ShutdownOnSuccess<String>()

        way to force this
            CompletableFuture<?> future1 = ...
            CompletableFuture<?> future2 = ...
            allOfTerminateOnFailure(future1, future2)
            ...
        }
        public static CompletableFuture<?> allOfTerminateOnFailure(CompletableFuture<?>... futures) {
            CompletableFuture<?> failure = new CompletableFuture<>();
            for (CompletableFuture<?> future : futures) {
                future.exceptionally(ex -> {
                    failure.completeExceptionally(ex);
                    future.cancel(true);
                    return null;
                });
            }
            return CompletableFuture.anyOf(failure, CompletableFuture.allOf(futures));
        }
    */
}
