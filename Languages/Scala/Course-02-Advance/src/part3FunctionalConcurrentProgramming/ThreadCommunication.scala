package part3FunctionalConcurrentProgramming

import scala.collection.mutable
import scala.util.Random

object ThreadCommunication extends App {

  /*
    LVL 1
    The producer-consumer problem

    producer -> [ X ] -> consumer [ ]
    producer -> [ ? ] -> consumer
  */

  class SimpleContainer {
    private var value: Int = 0

    def isEmpty: Boolean = value == 0
    def set(newValue: Int): Unit = value = newValue
    def get: Int = {
      val result = value
      value = 0
      result
    }
  }

  def naiveProdConsumer(): Unit = {
    val container = new SimpleContainer

    val consumer = new Thread(() => {
      println("[consumer] waiting...")
      while(container.isEmpty) {
        println("[consumer] actively waiting...")
      }
      println("[consumer] I have consumed " + container.get)
    })

    val producer = new Thread(() => {
      println("[producer] computing...")
      Thread.sleep(500)
      val value = 42
      println("[producer] I have produced, after a long work, the value " + value)
      container.set(value)
    })

    consumer.start()
    producer.start()
  }

  //naiveProdConsumer()

  // wait and notify tools
  /*
    - Make not assumptions about who get the lock first
    - Keep locking to a minimum
    - Maintain thread safety at ALL the times in parallel applications

    Wait and Notify only works in synchronized sections
    wait() used in a monitor released the lock and wait a notification to allows continue, when the notifications arrives locks again the monitors and continues
    notify() signal to INE sleeping thread they may continue, witch thread?? You don't know
    notifyAll() to awaken ALL threads
  */

  def smartProdConsumer(): Unit = {
    val container = new SimpleContainer

    val consumer = new Thread(() => {
      println("[consumer] waiting...")
      container.synchronized {
        container.wait()
      }
      println("[consumer] I have consumed " + container.get)
    })

    val producer = new Thread(() => {
      println("[producer] Hard at work")
      Thread.sleep(2000)
      val value = 42
      container.synchronized {
        println("[producer] I'm producing " + value)
        container.set(value)
        container.notify()
      }
    })

    consumer.start()
    producer.start()
  }

  //smartProdConsumer()

  /*
    LVL 2
    producer -> [? ? ?] -> consumer
  */

  def bufferProdConsumer(): Unit = {
    val buffer: mutable.Queue[Int] = new mutable.Queue[Int]
    val capacity = 3

    val consumer = new Thread(() => {
      val random = new Random()

      while(true) {
        buffer.synchronized {
          if (buffer.isEmpty) {
            println("[consumer] buffer empty, waiting...")
            buffer.wait()
          }

          //Here must be at least ONE value in the buffer
          val x = buffer.dequeue()
          println("[consumer] consumed " + x)

          buffer.notify() //notifies buffer is not full
        }

        Thread.sleep(random.nextInt(500))
      }
    })

    val producer = new Thread(() => {
      val random = new Random()
      var i = 0

      while(true) {
        buffer.synchronized {
          if (buffer.size == capacity) {
            println("[producer] buffer is full, waiting...")
            buffer.wait()
          }
          //Here must be at least ONE empty space in the buffer
          println("[producer] producing " + i)
          buffer.enqueue(i)

          buffer.notify() //notifies buffer is not empty

          i += 1
        }

        Thread.sleep(random.nextInt(500))
      }
    })

    consumer.start()
    producer.start()
  }

  //bufferProdConsumer()

  /*
    LVL 3
    producerN -> [? ? ?] -> consumerN

    problem LVL2 solution: producer produces a value, two consumer are waiting and producer notifies ONE consumer
    then the consumer notifies, but can notify instance of the producer the other consumer and it will consume nothing
  */
  def bufferMultipleProdConsumer(nConsumers: Int, nProducers: Int): Unit = {
    val buffer: mutable.Queue[Int] = new mutable.Queue[Int]
    val capacity = 3

    (1 to nConsumers).map(new Consumer(_, buffer)).foreach(_.start)
    (1 to nProducers).map(new Producer(_, buffer, capacity)).foreach(_.start)
  }

  class Consumer(id: Int, buffer: mutable.Queue[Int]) extends Thread {
    override def run(): Unit = {
      val random = new Random()
      while (true) {
        buffer.synchronized {
          while (buffer.isEmpty) { //replacing this if for a while, when awaken it will recheck the condition and only goes to consume if true
            println(s"[consumer $id] buffer empty, waiting...")
            buffer.wait()
          }

          //Here must be at least ONE value in the buffer
          val x = buffer.dequeue()
          println(s"[consumer $id] consumed " + x)

          buffer.notify() //notifies buffer is not full
        }

        Thread.sleep(random.nextInt(500))
      }
    }
  }

  class Producer(id: Int, buffer: mutable.Queue[Int], capacity: Int) extends Thread {
    val random = new Random()
    var i = 0

    while(true) {
      buffer.synchronized {
        while (buffer.size == capacity) {
          println(s"[producer $id] buffer is full, waiting...")
          buffer.wait()
        }
        //Here must be at least ONE empty space in the buffer
        println(s"[producer $id] producing " + i)
        buffer.enqueue(i)

        buffer.notifyAll() //notifies buffer is not empty read attention

        i += 1
      }

      Thread.sleep(random.nextInt(500))
    }
  }

  //bufferMultipleProdConsumer(3, 3)

  //Attention
  /*
    Sometimes notifyAll can prevents deadlocks

    10 producer and 2 consumers, buffer size = 3
    1. One producer fills the buffer the other nine go to sleep and same for this producer
    2. One consumer consumes all, then goes to sleep. The other goes to sleep once they see the buffer empty
    3. After 3 notifications, 3 producers wake up, fills the space, notifications goes to other producers
    4. Every producers sees the buffer full and goes to sleep
    5. Deadlock
  */

  //Exercise
  /*
    1. Create a deadlock
  */

  case class Friend(name: String) {
    def bow(otherFriend: Friend): Unit = {
      this.synchronized {
        println(s"$this: I am bowing to my friend $otherFriend")
        otherFriend.rise(this)
        println(s"$this: my friend $otherFriend has risen")
      }
    }

    def rise(otherFriend: Friend): Unit = {
      this.synchronized {
        println(s"$this: I am rising to my friend $otherFriend")
      }
    }

    var side = "right"
    def switchSide(): Unit = {
      if (side == "right") side = "left"
      else side = "right"
    }

    def pass(other: Friend): Unit = {
      while (this.side == other.side) {
        println(s"$this: Oh, but please, $other, feel free to pass...")
        switchSide()
        Thread.sleep(1000)
      }
    }
  }

  val sam = Friend("Sam")
  val pierre = Friend("Pierre")

  //new Thread(() => sam.bow(pierre)).start()  //Sam executes his block till pierre part and at the same time pierre executes his block till sam part and when each one call other part its blocked
  //new Thread(() => pierre.bow(sam)).start()

  /*
    2. Create a livelock
  */
  new Thread(() => sam.pass(pierre)).start()
  new Thread(() => pierre.pass(sam)).start()
}
