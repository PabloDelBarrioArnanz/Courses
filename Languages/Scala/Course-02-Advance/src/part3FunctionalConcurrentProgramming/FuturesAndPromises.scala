package part3FunctionalConcurrentProgramming

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration.*
import scala.util.{Failure, Random, Success}

object FuturesAndPromises extends App {

  def calculateMeaningOfLife: Int = {
    Thread.sleep(2000)
    42
  }

  //Future(calculateMeaningOfLife)(global)
  val aFuture = Future { //This will fail if we don't import a ExecutionContext
    calculateMeaningOfLife
  } //(global) implicit by the compiler

  println(aFuture.value) //None -> Option[Try[Int]]
  println("Waiting for the future")
  aFuture.onComplete {
    case Success(meaningOfLife) => println(s"The meaning of life is $meaningOfLife")
    case Failure(exception) => println(s"I have failed with $exception")
  } //This callback will be executed by SOME thread, not necessary the thread witch executed the future

  Thread.sleep(3000)

  //Mini social network
  case class Profile(id: String, name: String) {
    def poke(anotherProfile: Profile) = println(s"This ${this.name} poking ${anotherProfile.name}")
  }

  object SocialNetwork {
    //DB
    val names = Map(
      "fb.id.1-zuck" -> "Mark",
      "fb.id.2-bill" -> "Bill",
      "fb.id.3-dummy" -> "Dummy"
    )
    val friends = Map(
      "fb.id.1-zuck" -> "fb.id.2-bill"
    )

    val random = new Random()

    //API
    def fetchProfile(id: String): Future[Profile] = Future {
      Thread.sleep(random.nextInt(300))
      Profile(id, names(id))
    }

    def fetchBestFriend(profile: Profile): Future[Profile] = Future {
      Thread.sleep(random.nextInt(400))
      val bfId = friends(profile.id)
      Profile(bfId, names(bfId))
    }
  }

  //client: mark to poke bill
  val mark: Future[Profile] = SocialNetwork.fetchProfile("fb.id.1-zuck")
  mark.onComplete {
    case Success(markProfile) =>
      val bill = SocialNetwork.fetchBestFriend(markProfile)
      bill.onComplete {
        case Success(billProfile) => markProfile.poke(billProfile)
        case Failure(e) => e.printStackTrace()
      }
    case Failure(e) => e.printStackTrace()
  }

  //Functional composition of futures
  //map, flatMap, filter
  val nameOnTheWall: Future[String] = mark.map(profile => profile.name)

  val marksBestFried: Future[Profile] = mark.flatMap(profile => SocialNetwork.fetchBestFriend(profile))

  val zucksBestFriendRestricted = marksBestFried.filter(profile => profile.name.startsWith("Z"))

  //for-comprehensions
  for {
    mark <- SocialNetwork.fetchProfile("fb.id.1-zuck")
    bill <- SocialNetwork.fetchBestFriend(mark)
  } mark.poke(bill)

  Thread.sleep(1000)

  //fallbacks
  val aProfileNoMattersWhat = SocialNetwork.fetchProfile("unknown id").recover { //receives a PartialFunction from Throwable to U
    case e: Throwable => Profile("fb.id.0-dummy", "Forever alone")
  }

  val aFetchedProfileNoMatterWhat = SocialNetwork.fetchProfile("unknown id").recoverWith { //receives a PartialFunction from Throwable to Future[U]
    case e: Throwable => SocialNetwork.fetchProfile("fb.id.0-dummy")
  }

  val fallbackResult = SocialNetwork.fetchProfile("unknown id").fallbackTo(SocialNetwork.fetchProfile("fb.id.0-dummy")) //If fails first and second the exception in fallback result will be the first


  //Online banking app
  case class User(name: String)
  case class Transaction(sender: String, receiver: String, amount: Double, status: String)

  object BankingApp {
    val name = "Rock the JVM Banking"

    def fetchUser(name: String): Future[User] = Future {
      //simulate fetching the DB
      Thread.sleep(500)
      User(name)
    }

    def createTransaction(user: User, merchantName: String, amount: Double): Future[Transaction] = Future {
      //simulate some process
      Thread.sleep(1000)
      Transaction(user.name, merchantName, amount, "SUCCESS")
    }

    def purchase(username: String, item: String, merchantName: String, cost: Double): String = {
      //fetch the user from the DB
      //create a transaction
      //WAIT for the transaction to finish
      val transactionStatusFuture = for {
        user <- fetchUser(username)
        transaction <- createTransaction(user, merchantName, cost)
      } yield  transaction.status

      //Block a future 2 seconds
      Await.result(transactionStatusFuture, 2.seconds) //2.seconds implicit conversion -> pimp my library
    }
  }

  println(BankingApp.purchase("Daniel", "iPhone12", "Amazon", 3000)) //SUCCESS

  //PROMISES - Wrapper de un futuro - Pattern -> Este paradigma permite al separar la lectura y handling de la manipulacion, eliminar los problemas de concurrencia.
  //                                             Permite determinar cuando y como settear el valor de un futuro
  //Productor y consumidor comparten promesa, el productor settea el valor del futuro en la promesa, con promise.success(..) o failure y el consumidor extrae de la promesa el futuro porque el no manipula el valor
  // y se queda esperando al resultado -> promise.future.onComplete(...

  val promise = Promise[Int]() //"controller" from a future
  val future = promise.future

  //Thread 1 - "consumer"
  future.onComplete {
    case Success(r) => println("[consumer] I've received " + r)
  }

  //Thread 1 - "producer"
  val producer = new Thread(() => {
    println("[producer] crunching numbers...")
    Thread.sleep(1000)
    promise.success(42) //manipulates the internal future setting a value
    //promise.failure(...)
    println("[producer] done")
  })

  producer.start()
  Thread.sleep(1000)
}
