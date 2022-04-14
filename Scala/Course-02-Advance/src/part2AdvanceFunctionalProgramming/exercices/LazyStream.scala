package part2AdvanceFunctionalProgramming.exercices

import scala.annotation.tailrec
import scala.collection.immutable
import scala.collection.immutable.Stream
import scala.runtime.Nothing$


abstract class MyStream[+A] {
  def isEmpty: Boolean
  def head: A
  def tail: MyStream[A]

  def #::[B >: A](elem: B): MyStream[B] //prepend operator
  def ++[B >: A](anotherStream: => MyStream[B]): MyStream[B]

  def foreach(f: A => Unit): Unit
  def map[B](f: A => B): MyStream[B]
  def flatMap[B](f: A => MyStream[B]): MyStream[B]
  def filter(predicate: A => Boolean): MyStream[A]

  def take(n: Int): MyStream[A] //Take the first n elements out of this stream
  def takeAsList(n: Int): List[A] = take(n).toList()

  /*
    [1,2,3].toList([]) =
    [2,3].toList([1]) =
    [3].toList([2, 1]) =
    [].toList([3, 2, 1]) =
  */
  @tailrec
  final def toList[B >: A](acc: List[B] = Nil): List[B] = {
    if (isEmpty) acc.reverse
    else tail.toList(head :: acc)
  }
}

object EmptyStream extends MyStream[Nothing] {

  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyStream[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true

  override def #::[B >: Nothing](elem: B): MyStream[B] = new Cons(elem, this)
  override def ++[B >: Nothing](anotherStream: => MyStream[B]): MyStream[B] = anotherStream

  override def foreach(f: Nothing => Unit): Unit = ()
  override def map[B](f: Nothing => B): MyStream[B] = this
  override def flatMap[B](f: Nothing => MyStream[B]): MyStream[B] = this
  override def filter(predicate: Nothing => Boolean): MyStream[Nothing] = this

  override def take(n: Int): MyStream[Nothing] = this
}

class Cons[+A](hd: A, tl: => MyStream[A]) extends MyStream[A] {

  override val head: A = hd
  override lazy val tail: MyStream[A] = tl
  override def isEmpty: Boolean = false

  override def #::[B >: A](elem: B): MyStream[B] = new Cons(elem, this)
  override def ++[B >: A](anotherStream: => MyStream[B]): MyStream[B] = new Cons(head, tail ++ anotherStream)

  override def foreach(f: A => Unit): Unit = { //no lazy
    f(head)
    tail.foreach(f)
  }

  /*
    s = new Cons(1, ?)
    mapped = s.map(_ + 1) = new Cons(2, s.tail.map(_ + 1))
  */
  override def map[B](f: A => B): MyStream[B] = new Cons(f(head), tail.map(f)) //preserves lazy eval for tail
  override def flatMap[B](f: A => MyStream[B]): MyStream[B] = f(head) ++ tail.flatMap(f)
  override def filter(predicate: A => Boolean): MyStream[A] =
    if (predicate(head)) new Cons(head, tail.filter(predicate))
    else tail.filter(predicate)

  override def take(n: Int): MyStream[A] =
    if (n <= 0) EmptyStream
    else if (n == 1) new Cons(head, EmptyStream)
    else new Cons(head, tail.take(n-1))
}

object MyStream {
  def from[A](start: A)(generator: A => A): MyStream[A] =
    new Cons(start, MyStream.from(generator(start))(generator))
}

object LazyStream extends App {

  /*val naturals = MyStream.from(1)(_ + 1)
  println(naturals.head)
  println(naturals.tail.head)
  println(naturals.tail.tail.head)

  val startFrom0 = 0 #:: naturals //naturals.#::(0)
  println(startFrom0.head)

  startFrom0.take(200).foreach(number => print(number + ", "))
  println
  println(startFrom0.map(_ * 2).take(100).toList())
  println(startFrom0.flatMap(x => new Cons(x, new Cons(x + 1, EmptyStream))).take(10).toList())
  println(startFrom0.filter(_ < 10).take(5).toList())
  //println(startFrom0.filter(_ < 10).take(11).toList()) will crash because only found 10 elements and will search the 11th to the infinite
  println(startFrom0.filter(_ < 10).take(10).take(20).toList()) //this not crash because first will take 10 and that is a finite stream
  println*/

  //Fibonacci numbers
  // [first, [...
  // [first, fibonacci(second, first + second)...
  def fibonacci(first: Int, second: Int): MyStream[Int] =
    new Cons(first, fibonacci(second, first + second))

  println(fibonacci(1, 1).take(15).toList())

  //Prime numbers with Eratosthenes' sieve
  /*
    [2, 3, 4..] filter out all divisible by 2
    [2, 3, 5..] filter out all divisible by 3
    [2, 3, 5..] filter out all divisible by ..
  */
  def eratosthenes(numbers: MyStream[Int]): MyStream[Int] =
    if (numbers.isEmpty) numbers
    else new Cons(numbers.head, eratosthenes(numbers.tail.filter(_ % numbers.head != 0)))

  println(eratosthenes(MyStream.from(2)(_ + 1)).take(100).toList())

}