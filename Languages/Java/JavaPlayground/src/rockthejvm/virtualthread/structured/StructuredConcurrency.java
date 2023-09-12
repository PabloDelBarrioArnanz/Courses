package rockthejvm.virtualthread.structured;

public class StructuredConcurrency {

    /*
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

        As VTs behaviour is structured we can do that things similar to coroutines
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

        ----------------------------------------------------------------------------------------------------------------

        In older Java versions we can emulate it

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
