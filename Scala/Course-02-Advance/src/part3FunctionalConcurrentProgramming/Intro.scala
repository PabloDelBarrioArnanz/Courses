package part3FunctionalConcurrentProgramming

import java.util.concurrent.Executors

object Intro extends App {

  /*
    Interface Runnable {
      public void run()
    }
  */
  // JVM Threads
  val runnable = new Runnable {
    override def run(): Unit = println("Running in parallel")
  }
  val aThread = new Thread(runnable)

  aThread.start() //gives the signal to he JVM to start a JVM thread
  //create a JVM thread => OS thread

  //runnable.run() this is not parallel, will be executed in the main thread

  aThread.join() //Block til aThread finished the running

  println

  val threadHello = new Thread(() => (1 to 5).foreach(_ => println("Hello")))
  val threadGoodbye = new Thread(() => (1 to 5).foreach(_ => println("Goodbye")))

  threadHello.start()
  threadGoodbye.start()
  //Different runs produces different results

  //Executors
  val pool = Executors.newFixedThreadPool(10) //10 thread at max disposal to executed jobs
  pool.execute(() => println("Something in the thread pool"))

  pool.execute(() => {
    Thread.sleep(1000)
    println("Done after 1 second")
  })

  pool.execute(() => {
    Thread.sleep(1000)
    println("Almost done")
    Thread.sleep(1000)
    println("Done after 2 seconds")
  })

  pool.shutdown()
  //pool.execute(() => println("Should not appear")) throws an exception pool not available after shutdown it

  //pool.shutdownNow() Stops the pool right now also the running threads
  println(pool.isShutdown) // Will show true before before executions ends, because doesn't accept more
}
