package part3FunctionalConcurrentProgramming.exercices

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration.*
import scala.util.{Failure, Random, Success, Try}


object FuturesAndPromiseExercises extends App {

  /*
    1- Fulfill a future IMMEDIATELY with a value
    2- Run a function inSequence(fa, fb) -> run the fb when fa its complete
    3- first(fa, fb) => new Future with the first value of the two futures
    4- first(fa, fb) => new Future with the last value of the two futures
    5- retryUntil(action: () => Future[T], condition: T => Boolean): Future[T]
  */

  //Exercise - 1
  def fulFillImmediately[T](value: T): Future[T] = Future(value)

  //Exercise - 2
  def inSequence[A, B](first: Future[A], second: Future[B]): Future[B] = first.flatMap(_ => second)

  //Exercise - 3
  def first[A](fa: Future[A], fb: Future[A]): Future[A] = {
    val promise = Promise[A]()

    fa.onComplete(promise.tryComplete) //tryComplete instanceOf success because if its already complete will throw exception
    fb.onComplete(promise.tryComplete)

    promise.future
  }

  //Exercise - 4
  def last[A](fa: Future[A], fb: Future[A]): Future[A] = {
    val bothPromise = Promise[A]()
    val lastPromise = Promise[A]()

    def checkComplete = (result: Try[A]) => if (!bothPromise.tryComplete(result)) lastPromise.complete(result)

    fa.onComplete(checkComplete)
    fb.onComplete(checkComplete)

    lastPromise.future
  }

  //Exercise - 5
  def retryUntil[A](action: () => Future[A], condition: A => Boolean): Future[A] =
    action()
      .filter(condition)
      .recoverWith {
        case _ => retryUntil(action, condition)
      }


  val fast = Future {
    Thread.sleep(100)
    42
  }

  val slow = Future {
    Thread.sleep(200)
    45
  }

  first(fast, slow).foreach(first => println("FIRST " + first))
  last(fast, slow).foreach(last => println("LAST " + last))

  val random = new Random()
  val numberGenerator = () => Future {
    val number = random.nextInt(100)
    println(s"Generated $number")
    number
  }

  retryUntil(numberGenerator, (x: Int) => x < 10).foreach(result => println("settled at " + result))

  Thread.sleep(10000)
}
