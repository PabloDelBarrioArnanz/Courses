package part3FunctionalConcurrentProgramming

object JVMConcurrencyProblems {

  def runInParallel(): Unit = {
    var x = 0

    val thread1 = new Thread(() => {
      x = 1
    })

    val thread2 = new Thread(() => {
      x = 2
    })

    thread1.start()
    thread2.start()
    println(x) //race condition
  }

  case class BankAccount(var amount: Int)

  def buy(bankAccount: BankAccount, thing: String, price: Int): Unit = {
    bankAccount.amount -= price //This line are 3 ops: read amount, compute amount and write new amount
  }

  def buySafe(bankAccount: BankAccount, thing: String, price: Int): Unit = {
    bankAccount.synchronized { //Critical section is wrapped with synchronized, does not allow multiple threads to run the critical section at the same time
      bankAccount.amount -= price
    }
  }

  /*
    Example of race condition:
    thread1(shoes)
      - reads amount 50000
      - computes result 50000 - 3000 = 47000
    thread2(iPhone)
      - reads amount 50000
      - computes result 50000 - 3000 = 46000
    thread1(shoes)
      - writes amount 47000
    thread2(iPhone)
      - writes amount 46000
  */
  def demoBankingProblem(): Unit = {
    (1 to 10000).foreach{_ =>
      val account = BankAccount(50000)
      val thread1 = new Thread(() => buy(account, "shoes", 3000))
      val thread2 = new Thread(() => buy(account, "iPhone", 4000))
      thread1.start()
      thread2.start()
      thread1.join()
      thread2.join()
      if (account.amount != 43000) println(s"Broken bank ${account.amount}")
    }
  }

  //Exercises
  /*
    1 - Create "inception threads"
      thread1
        -> creates a thread2
          -> creates a thread3
            -> ...
      each thread prints hello from thread $i
    print all messages in reverse order
  */
  def inceptionThreads(maxThreads: Int, i: Int = 1): Thread = {
    new Thread(() => {
      if (i < maxThreads) {
        val newThread = inceptionThreads(maxThreads, i + 1) //When execution arrives here calls the method til threads are equals to i and then executes print from latest to olds
        newThread.start()
        newThread.join()
      }
      println(s"Hello from thread $i")
    })
  }

  /*
    2 - Whats the max/min value of x
    max = 100
    min = 1 all threads read at the same time computes and write 1
  */
  def minMaxX(): Unit = {
    var x = 0
    val threads = (1 to 100).map(_ => new Thread(() => x+=1))
    threads.foreach(_.start)
  }

  /*
    3 - Sleep fallacy: whats the value of message
    most of the case will be Scala is awesome, but sometimes could be slower than 1ms to return the job and make message = Scala is awesome and mainThread continues with no wait awesome thread
  */
  def demoSleepFallacy(): Unit = {
    var message = ""
    val awesomeThread = new Thread(() => {
      Thread.sleep(1000)
      message = "Scala is awesome"
    })

    message = "Scala sucks"
    awesomeThread.start()
    Thread.sleep(1001)
    //solution for wait exactly no wait, make a join
    println(message)
  }

  def main(args: Array[String]): Unit = {
    //runInParallel() //Sometimes 1 sometimes 2
    //demoBankingProblem()

    //Exercises
    inceptionThreads(5).start()
    //minMaxX()
    demoSleepFallacy()
  }
}
