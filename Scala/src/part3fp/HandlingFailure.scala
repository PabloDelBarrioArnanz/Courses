package part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Super Failure"))

  def unsafeMethod(): String = throw new RuntimeException("No String 4u")

  //Try objects
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  val anotherPotentialFailure = Try {
    //code that might throw
  }

  //utilities
  println(potentialFailure.isSuccess)
  println(potentialFailure.isFailure)

  println(potentialFailure orElse Try("Backup text"))
  println(potentialFailure getOrElse "Backup text")

  def betterUnSafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid string")
  val betterFallback = betterUnSafeMethod orElse betterBackupMethod

  val potentialNumber: Try[Int] = Try(3)
  println(potentialNumber.map(_ * 2))
  println(potentialNumber.flatMap(x => Success(x * 10)))

  //for-comprehension

  val hostname = "localhost"
  val port = "8080"

  def renderHTML(page: String): Unit = println(page)

  class Connection {
    def get: String = {
      if (new Random(System.nanoTime).nextBoolean) "<html>"
      else throw new RuntimeException("Connection interrupted")
    }
    def getSafe: Try[String] = {
      if (new Random(System.nanoTime).nextBoolean) Success("<html>")
      else Failure(new RuntimeException("Connection interrupted"))
    }
  }

  object HttpService {
    def getConnection(host: String, port: String): Connection =
      if (new Random(System.nanoTime).nextBoolean) new Connection
      else throw new RuntimeException("Someone else took the port")

    def getSafeConnection(host:String, port: String): Try[Connection] =
      if (new Random(System.nanoTime).nextBoolean) Success(new Connection)
      else Failure(new RuntimeException("Someone else took the port"))
  }

  Try(HttpService.getConnection(hostname, port))
    .map(_.get())
    .foreach(println)

  for{
    connection <- HttpService.getSafeConnection(hostname, port)
    webPage <- connection.getSafe
  } println(webPage)
}
