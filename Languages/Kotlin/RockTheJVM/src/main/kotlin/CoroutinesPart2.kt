import kotlinx.coroutines.*
import java.util.concurrent.Executors


/*
    Cooperative Scheduling
    Coroutines yield manually, when a coroutine is working and not sleeping will never yield automatically as Java VTs
*/

suspend fun workingHard() {
    logger.info("WorkingHard")
    while (true) {
        // CPU intensive computation not going to yield
    }
    delay(100)
    logger.info("Work done")
}

suspend fun takeABreak() {
    logger.info("Taking a break")
    delay(1000)
    logger.info("Break done")
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun workHardRoutine() {
    val dispatcher: CoroutineDispatcher = Dispatchers.Default.limitedParallelism(1) // Force only 1 thread
    coroutineScope {
        launch(dispatcher) {
            workingHard()
        }
        launch(dispatcher) {
            takeABreak()
        }
    }
}

suspend fun workingNicely() {
    logger.info("WorkingNicely")
    while (true) {
        delay(100) // In that case how it's a sleep instance of a cpu computation yield will be automatically
    }
    delay(100)
    logger.info("Work done")
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun workingNicelyRoutine() {
    val dispatcher: CoroutineDispatcher = Dispatchers.Default.limitedParallelism(1) // Force only 1 thread
    coroutineScope {
        launch(dispatcher) {
            workingNicely()
        }
        launch(dispatcher) {
            takeABreak()
        }
    }
}

// Dispatchers

val simpleDispatcher = Dispatchers.Default // For "normal code" = short or yielding coroutines
val blockingDispatcher = Dispatchers.IO // For blocking code (DB connection, Web, long-running computations)
val customDispatcher = Executors.newFixedThreadPool(8).asCoroutineDispatcher() // on top of your own pool


// Cancellation
suspend fun forgettingFriendBirthdayRoutine() {
    coroutineScope {
        val workingJob = launch { workingNicely() }
        launch {
            delay(2000) // after 2s I remember I have a birthday today
            workingJob.cancel() // Send signal to the coroutine to cancel, the coroutine will execute the cancellation at first yielding point
            workingJob.join() // Here we are sure that the cancellation has been cancelled
            logger.info("I forgot my friend's birthday!")
        }
    }
}

// If a coroutine doesn't yield, it can't be cancelled
suspend fun forgettingFriendBirthdayRoutineUnCancellable() {
    coroutineScope {
        val workingJob = launch { workingHard() }
        launch {
            delay(2000) // after 2s I remember I have a birthday today
            logger.info("Trying to stop working...")
            workingJob.cancel() // Send signal to the coroutine to cancel, as the thread never yield, never will be cancelled
            workingJob.join() // Blocking
            logger.info("I forgot my friend's birthday!")
        }
    }
}

// Resources
class Desk : AutoCloseable { // Java Interface for try-with-resources
    init {
        logger.info("Starting to work on this seat")
    }

    override fun close() {
        logger.info("Cleaning up the desk")
    }
}

suspend fun forgettingFriendBirthdayRoutineWithResources1() {
    val desk = Desk() // resource
    coroutineScope {
        val workingJob = launch {
            // use the resource here
            desk.use { _ -> // This resource will be closed upon completion of the coroutine (end or cancel) like a try-with-resources in java
                workingNicely()
            }
        }
        launch {
            delay(2000) // after 2s I remember I have a birthday today
            workingJob.cancel() // Send signal to the coroutine to cancel, as the thread never yield, never will be cancelled
            workingJob.join() // Blocking
            logger.info("I forgot my friend's birthday!")
        }
    }
}

suspend fun forgettingFriendBirthdayRoutineWithResources2() {
    val desk = Desk() // resource
    coroutineScope {
        val workingJob = launch {
            // use the resource here
            desk.use { _ -> // This resource will be closed upon completion of the coroutine (end or cancel) like a try-with-resources in java
                workingNicely()
                // If we have more resources will be queued and released
            }
        }
        // can also define your own "cleanup" code in case of completion
        workingJob.invokeOnCompletion { exception: Throwable? ->
            // can handle completion and cancellation depending on the exception
            logger.info("Make sure I talk to my colleagues that I'll be out for 30 mins")
        }
        launch {
            delay(2000) // after 2s I remember I have a birthday today
            workingJob.cancel() // Send signal to the coroutine to cancel, as the thread never yield, never will be cancelled
            workingJob.join() // Blocking
            logger.info("I forgot my friend's birthday!")
        }
    }
}

// Cancellation to child coroutines
suspend fun drinkWater() {
    while (true) {
        logger.info("Drinking water")
        delay(1000);
    }
}

suspend fun forgettingFriendBirthdayStayHydrated() {
    coroutineScope {
        val workingJob = launch { // When cancelling this every process inside will be cancelled
            launch { workingNicely() }
            launch { drinkWater() }
        }
        launch {
            delay(2000) // after 2s I remember I have a birthday today
            workingJob.cancel() // Send signal to the coroutine to cancel, the coroutine will execute the cancellation at first yielding point
            workingJob.join() // Here we are sure that the cancellation has been cancelled
            logger.info("I forgot my friend's birthday!")
        }
    }
}

// Coroutines context
suspend fun asynchronousGreeting() {
    coroutineScope {
        launch(CoroutineName("GreetingCoroutine") + Dispatchers.Default) { // Set name and dispatcher = CoroutineContext
            logger.info("Hello, everyone!")
        }
    }
}

suspend fun demoContextInheritance() {
    coroutineScope {
        launch(CoroutineName("GreetingCoroutine")) { // This context will be inherited for all children
            logger.info("[parent coroutine] Hi there!")
            launch {
                logger.info("[child coroutine] Hi there!")
            }
            launch(CoroutineName("GreetingCoroutine")) { // Overriden context
                logger.info("[child coroutine] Hi there!")
            }
            delay(200)
            logger.info("[child coroutine] Hi again from parent!")
        }
    }
}

suspend fun main() {
    forgettingFriendBirthdayStayHydrated()
}