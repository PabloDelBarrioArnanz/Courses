## Info from youtube video see also java files from RockTheJVM
### Java 19 - Game of Loom: Implementation patterns and performance implications playing with virtual threads

#### JDK native threads are thin wrappers around operating system threads
- Native threads are costly and memory consuming
- You can't have too many of them (approx 2000)

#### Virtual threads make a better use of the OS threads getting rid of the 1:1 relationship with os threads
* Many VT can be multiplexed on the same OS thread (carrier) (ForkJoinPool of native threads carriers)
* When a VT calls a blocking operation the JDK performs a nonblocking os call and automatically suspends de VT until the operation finished 
* You can have millions of them

#### Why we need them? Wrong reason
* NT
  * 2kB metadata
  * PreAllocated 1MB stack
  * 1-10µs for context switch
* VT
  * 200-300B metadata
  * Allocated on heap
  * Some ns or below 1µs for context switch

#### Why we need them? Right reason
VT allow to write asynchronous code in a way that looks synchronous
The main goal of VT is having scalable apps without paying the of more complex code
VT                  - High program scalability and Less Program Complexity
Reactive Programing - High program scalability and High Program Complexity
One Thread per task - Less program scalability and Less Program Complexity
VT are perfect for waiting

#### Spiral of Innovation
 * Green Thread &rarr; Native Thread &rarr; Events (actors/messages) &rarr; Reactive Programing &rarr; Virtual Threads

#### Virtual Threads as Syntactic Sugar for reactive programming
 * VT do no enable anything that was impossible before
 * VT allow to achieve the same result of reactive programing in a much easier and less verbose way
 * Loom is for concurrent programming what lambdas has been for functional programming

This is a bit fake, VT also provide us a better observability/troubleshooting support

#### Possible issues with Virtual Threads
 * Loom uses the Java heap to store VTs stacks and other metadata, with large numbers of VT this may have a relevant impact on GC
 * Pinning: synchronization blocks and other specific constructs (like JNI functions) are not recognized as blocking operations, thus actually blocking not only the VT also its carrier
 * Massive context switches (in user space) of a huge number of VT can potentially cause a large amount of cache misses
 * VT scheduler is not pluggable, hardcoded to the ForkJoinPool scheduler how it uses work stealing may a thread awakes in other carrier and
 * if it's in other core also this can trigger a L3 cache read operation instance of L1
 * VT are not pre-emptive, they cannot be de-scheduled while running heavy calculations without ever calling a JDK's blocking methods, so they are not a good fit for CPU-bound tasks when fairness is key (that means if your task doesn't perform blocking operations using VT there will be no context switching and will be less fair than native threads)
 * VT supports thread locals, but since they can be very numerous, thread locals could have a huge memory footprint. They could be replaced by Extent-Local Variables

#### Conclusions
VT aren't faster threads
 * They are perfect for waiting (be careful to pinning)
 * Their goal is maximizing the utilization of external resources and then improving throughput (I/O)
 * This is the same goal of reactive programming but in a much more readable and developers friendly way