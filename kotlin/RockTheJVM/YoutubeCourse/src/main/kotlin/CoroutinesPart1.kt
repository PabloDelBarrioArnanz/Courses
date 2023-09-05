/*
   Coroutine = "thread"
   Not a real thread in a classic way, a thread is giant data structure managed by the JVM and OS
   It's a heavy computation to change between threads context, and SO mustn't handle a big number of threads

  A coroutine instance is a lightweight thread, space thread, green thread or virtual threads
  is a block of code that is executed seemingly independently of others

  Kotlin has this system built in so that we can run heavy parallel and concurrent applications
  The way to create this functions is with the key word suspend

  VM options => -Dkotlinx.coroutines.debug to show the thread id
*/

import kotlinx.coroutines.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val logger: Logger = LoggerFactory.getLogger("CoroutinesPlayground")

suspend fun bathTime() {
    // Continuation
    logger.info("Going to hte bathroom")
    delay(500L) // semantic blocking
    // Continuation restore that way can be executed by other thread
    logger.info("Bath done, exiting")
    /*
        this function will print the first log with the main thread and the second one with a kotlinx.coroutines.DefaultExecutor thread
        This is bcs the delay blocks the flow, but not blocks like a classic sense, frees the thread to do something else and then the rest of the function is being executed on some other thread
        In scala this is called semantic blocking
        To work like this needs a bit of magic -> kotlin has a coroutine context or continuation (data structure) witch saves the local state (variables, line executing...) and this structure
        is being saved by the kotlin core routines
    */
}

/*
    Structured concurrency
    Coroutines scopes are isolated
    A coroutineScope ends when all code or suspends function are finished it's like a batch of parallel
    coroutineScope allows share context, cancellation... see next part
*/
suspend fun boilingWater() {
    logger.info("Boiling water")
    delay(1000L)
    logger.info("Water boiled")
}

// Sequential
suspend fun sequentialMorningRoutine() {
    // Setting both function in the same scope will have the same result
    coroutineScope {// Start a context for coroutines
        bathTime()
    }
    coroutineScope {
        boilingWater()
    }
}

// Concurrent
suspend fun concurrentMorningRoutine() {
    // Launch will start a new coroutine in parallel new Thread(() => ...).start
    coroutineScope {
        launch { bathTime() } // This coroutine is a child from coroutineScope
        launch { boilingWater() }
    }
}

// That way aren't children and error handling become difficult
suspend fun noStructuredConcurrentMorningRoutine() {
    GlobalScope.launch { bathTime() } // This coroutine is a child from coroutineScope
    GlobalScope.launch { boilingWater() }
}

/*
    Plan coroutines
    take a bath
    start boiling water

    after both  are done  => drink coffee
*/

suspend fun makeCoffee() {
    logger.info("Starting to make coffee")
    delay(500L)
    logger.info("Done coffee")
}

// Not the best way to do it
suspend fun morningRoutineWithCoffee() {
    coroutineScope {
        val bathTimeJob: Job = launch { bathTime() }
        val boilingWaterJob: Job = launch { boilingWater() }
        // Waiting both are done
        bathTimeJob.join() // semantic blocking
        boilingWaterJob.join()
        launch { makeCoffee() }
    }
}

suspend fun structuredMorningCoroutineWithCoffee() {
    coroutineScope {
        coroutineScope {
            launch { bathTime() }
            launch { boilingWater() }
        }
        // Remember coroutineScope only ends when all functions or suspend function are finished
        launch { makeCoffee() }
    }
}

/*
    Returning values from coroutines
    async return a deferred, launch no
    Deferred is a data structure that will be filled with the result when the function inside the async has been completed similar to  Future
*/
suspend fun makeJavaCoffee(): String {
    logger.info("Starting to make coffee")
    delay(500L)
    logger.info("Done coffee")
    return "Java Hot Coffee"
}

suspend fun toastingBread(): String {
    logger.info("Starting to make toasts")
    delay(1000L)
    logger.info("Toast are out")
    return "Toasted bread"
}

suspend fun prepareBreakfast() {
    coroutineScope {
        val coffee = async { makeJavaCoffee() } // Deferred<String>
        val toast = async { toastingBread() }

        val finalCoffee = coffee.await()
        val finalToast = toast.await()
        logger.info("I am eating a final toast and eating a final toast")
    }
}



// Suspend function only can be called from other suspend function or coroutines
suspend fun main() {
    prepareBreakfast()
    Thread.sleep(2000)
}