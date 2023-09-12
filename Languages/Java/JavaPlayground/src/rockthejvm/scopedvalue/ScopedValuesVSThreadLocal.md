### ScopedValues VS ThreadLocal

## What is a Scoped Value?
* Scoped values allow a value (i.e., any object) to be stored for a limited time in such a way that only the thread that wrote the value can read it.
* Scoped values are usually created as public static fields, so they can be retrieved from any method without us having to pass them as parameters to those methods.

Example
```
class Server {
  public final static ScopedValue<User> LOGGED_IN_USER = ScopedValue.newInstance();
 
  private void serve(Request request) {
    User loggedInUser = authenticateUser(request);
    ScopedValue.where(LOGGED_IN_USER, loggedInUser)
               .run(() -> restAdapter.processRequest(request));
  }
}
```
```
class AnotherClass {
 
  private void serve(Request request) {
    //Doing bussines
    User loggedInUser = Server.LOGGED_IN_USER.get();
  }
}
```
An alternative to pass the parameter through all the flow rest -> service -> .. -> repository where we use it

## Rebinding Scope
ScopedValue has no set method to change the stored value. </br>
Instead, you can rebind the value for the invocation of a limited code section, That means that, for this limited code section, another value is visible … and as soon as that section is terminated, the original value is visible again.

## Inheriting Scoped Values
Scoped values are automatically inherited by all child threads created via a Structured Task Scope.
Using StructuredTaskScope
```
class UseCase {
  public void invoke(UUID id) {
    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
      Future<Data>.   dataFuture    = scope.fork(() -> repository.getData(id));
      Future<ExtData> extDataFuture = scope.fork(() -> remoteService.getExtData(id));
 
      scope.join();
      scope.throwIfFailed();

      Data    data    = dataFuture.resultNow();
      ExtData extData = extDataFuture.resultNow();
    }
  }
}
```
This way, we can also access the logged-in user from the child threads created via fork(…) using LOGGED_IN_USER.get().

## What Is the Difference Between ScopedValue and ThreadLocal?
Scoped values have the following advantages:

* They are only valid during the lifetime of the Runnable passed to the where method, and they are released for garbage collection immediately afterward (unless further references to them exist). A thread-local value, on the other hand, remains in memory until either the thread is terminated (which may never be the case when using a thread pool) or it is explicitly deleted with ThreadLocal.remove(). Since many developers forget to do this (or don't do it because the program is so complex that it's not obvious when a thread-local value is no longer needed), memory leaks are often the result.
* A scoped value is immutable – it can only be reset for a new scope by rebinding, as mentioned above. This improves the understandability and maintainability of the code considerably compared to thread locals, which can be changed at any time using set().
* The child threads created by StructuredTaskScope have access to the scoped value of the parent thread. If, on the other hand, we use InheritableThreadLocal, its value is copied to each child thread, which can significantly increase the memory footprint.

Like thread locals, scoped values are available for both platform and virtual threads. Especially when there are thousands to millions of virtual child threads, the memory savings from accessing the scoped value of the parent thread (instead of creating a copy) can be significant.