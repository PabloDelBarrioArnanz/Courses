package part2AdvanceFunctionalProgramming

object LazyEvaluation extends App {

  //val x: Int = throw new RuntimeException -> crash
  lazy val x: Int = throw new RuntimeException //will only be evaluated when need for example if print(x)

  lazy val y: Int = {
    println("Hello")
    34
  }
  println(y) //hello 34
  println(y) //34
  //lazy expressions only will be evaluated once, next time will return previous value

  def generateValueEvenOrOdd: Boolean = {
    System.nanoTime() % 2 == 0
  }

  println(generateValueEvenOrOdd) //Always same result
  println(generateValueEvenOrOdd)
  println(generateValueEvenOrOdd)

  def sideEffectCondition: Boolean = {
    println("Bob")
    true
  }

  def simpleCondition: Boolean = false
  lazy val lazyCondition = sideEffectCondition

  println(if (simpleCondition && lazyCondition) "yes" else "no") //lazyCondition will never be evaluated bcs simpleCondition its false

  //CALL BE NEED
  def byNameMethod(n: => Int): Int = n + n + n + 1
  def byNameMethodLazy(n: => Int): Int = {
    lazy val t = n
    t + t + t + 1
  }
  def retrieveMagicValue = {
    //long computation
    Thread.sleep(1000)
    println("waiting")
    42
  }
  println(byNameMethod(retrieveMagicValue)) //receives a function by parameter and how its used 3 times inside the method, retrieveMagicValue will be executed 3 times
  println(byNameMethodLazy(retrieveMagicValue)) //This time retrieveMagicValue only will be executed once

  //filtering with lazy values
  def lessThan30(i: Int): Boolean = {
    println(s"$i is less than 30?")
    i < 30
  }

  def greaterThan20(i: Int): Boolean = {
    println(s"$i is greater than 20?")
    i > 20
  }
  println
  val numbers = List(1, 25, 40, 5, 23)
  val lt30 = numbers.filter(lessThan30) //(1, 25, 5, 23)
  val gt20 = lt30.filter(greaterThan20)
  println(gt20)

  println
  val lt30Lazy = numbers.withFilter(lessThan30) //lazy values under the hood
  val gt20Lazy = lt30Lazy.withFilter(greaterThan20)
  gt20Lazy.foreach(println) //instance of evaluate first filter for each one and then the second, now its evaluate both filters at the same time
  println(gt20Lazy)

  //for-comprehension use with filter with guards
  for {
    a <- List(1,2,3) if a % 2 == 0 //Use lazy values
  } yield a + 1

  List(1,2,3).withFilter(_ %  2 == 0).map(_ + 1)

  /*
    Exercise 1. Implement a lazily evaluated, singly linked STREAM of elements.

    naturals = MyStream.from(1)(x => x + 1) = stream of natural numbers (potentially infinite!)
    naturals.take(100) //lazily evaluated stream of the first 100 natural (finite stream)
    naturals.foreach(println) //will crash infinite
    naturals.map(_ * 2) //stream of all even numbers (potentially infinite)
  */

  abstract class MyStream[+A] {
    def isEmpty: Boolean
    def head: A
    def tail: MyStream[A]

    def #::[B >: A](elem: B): MyStream[A] //prepend operator
    def ++[B >: A](anotherStream: MyStream[B]): MyStream[B]

    def foreach(f: A => Unit): Unit
    def map[B](f: A => B): MyStream[B]
    def flatMap[B](f: A => MyStream[B]): MyStream[B]
    def filter(predicate: A => Boolean): MyStream[A]

    def take(n: Int): MyStream[A] //Take the first n elements out of this stream
    def takeAsList(n: Int): List[A]
  }

  object MyStream {
    def from[A](start: A)(generator: A => A): MyStream[A] = ???
  }

  //Exercises folder
}
