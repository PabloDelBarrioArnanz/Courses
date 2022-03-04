package part3fp

import scala.util.Random

object Optional extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  def unsafeMethod(): String = null

  //val result = Some(unsafeMethod()) //WRONG Some(null)
  val result = Option(unsafeMethod())

  println(result)

  //chained methods
  def backupMethod(): String = "A valid result"

  val chainedResult1 = Option(unsafeMethod()).orElse(Option(backupMethod()))
  val chainedResult2 = Option(unsafeMethod()).getOrElse(backupMethod()) //getOrElse only if we are sure

  def betterUnsafeMethod(): Option[String] = None

  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod

  println(myFirstOption.isEmpty)
  println(myFirstOption.get) //Do not use this
  println(myFirstOption.map(_ * 2)) //Some(8)
  println(myFirstOption.filter(_ < 10)) //None
  println(myFirstOption.flatMap(x => Option(x * 10)))

  //for-comprehensions
  val config: Map[String, String] = Map(
    "host" -> "176.128...",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean) Some(new Connection)
      else None
  }

  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  val connectionStatus = connection.map(c => c.connect)
  connectionStatus.foreach(println)

  //Chained calls
  config.get("host")
    .flatMap(h => config.get("host")
      .flatMap(p => Connection(h, p))
      .map(_.connect))
    .foreach(println)

  //for-comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)
}
