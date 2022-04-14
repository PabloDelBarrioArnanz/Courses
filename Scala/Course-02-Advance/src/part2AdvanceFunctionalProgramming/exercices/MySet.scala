package part2AdvanceFunctionalProgramming.exercices

import scala.annotation.tailrec
import scala.jdk.Accumulator
import scala.language.postfixOps


//Extends from a function which goes from A to Boolean because apply method goes from A to Boolean
trait MySet[A] extends (A => Boolean) {

  def apply(elem: A): Boolean = contains(elem)
  def contains(elem: A): Boolean
  def +(elem: A): MySet[A]
  def ++(anotherSet: MySet[A]): MySet[A]

  def map[B](f: A => B): MySet[B]
  def flatMap[B](f: A => MySet[B]): MySet[B]
  def filter(predicate: A => Boolean): MySet[A]
  def foreach(f: A => Unit): Unit

  def -(elem: A): MySet[A]
  def &(anotherSet: MySet[A]): MySet[A]
  def --(anotherSet: MySet[A]): MySet[A]

  def unary_! : MySet[A]
}

class EmptySet[A] extends MySet[A] {

  override def contains(elem: A): Boolean = false
  override def +(elem: A): MySet[A] = new NonEmptySet[A](elem, this)
  override def ++(anotherSet: MySet[A]): MySet[A] = anotherSet
  override def map[B](f: A => B): MySet[B] = new EmptySet[B]
  override def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]
  override def filter(predicate: A => Boolean): MySet[A] = this
  override def foreach(f: A => Unit): Unit = ()

  override def -(elem: A): MySet[A] = this
  override def --(anotherSet: MySet[A]): MySet[A] = this
  override def &(anotherSet: MySet[A]): MySet[A] = this

  override def unary_! : MySet[A] = new PropertyBasedSet[A](_ => true)
}

class NonEmptySet[A](head:A, tail: MySet[A]) extends MySet[A] {

  override def contains(elem: A): Boolean = elem == head || tail.contains(elem)

  override def +(elem: A): MySet[A] =
    if(this contains elem) this
    else new NonEmptySet[A](elem, this)

  /*
    [1 2 3] ++ [4 5] =
    [2 3] ++ [4 5] + 1 =
    [3] ++ [4 5] + 1 + 2 =
    [] ++ [4 5] + 1 + 2 + 3 = //here applies ++ from EmptySet
    [4 5] + 1 + 2 + 3 = [4 5 1 2 3]
  */
  override def ++(anotherSet: MySet[A]): MySet[A] = tail ++ anotherSet + head

  override def map[B](f: A => B): MySet[B] = (tail map f) + f(head) //new NonEmptySet(f.apply(head), tail.map(f))
  override def flatMap[B](f: A => MySet[B]): MySet[B] = (tail flatMap f) ++ f(head) //new NonEmptySet(f.apply(head), tail.map(f))

  override def filter(predicate: A => Boolean): MySet[A] = {
    val tailFiltered = tail.filter(predicate)
    if (predicate(head)) tailFiltered + head
    else tailFiltered
  }

  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail foreach f
  }

  override def -(elem: A): MySet[A] =
    if (head == elem) tail
    else tail - elem + head

  override def &(anotherSet: MySet[A]): MySet[A] = filter(anotherSet) // filter(x => anotherSet.contains(x))
  //override def --(anotherSet: MySet[A]): MySet[A] = filter(!anotherSet(_)) // filter(x => !anotherSet.contains(x))
  override def --(anotherSet: MySet[A]): MySet[A] = filter(!anotherSet) // with unary_!

  override def unary_! : MySet[A] = new PropertyBasedSet[A](x => !this.contains(x))
}

// All elements of type A which satisfy a property
// {x in A | property(x)}
class PropertyBasedSet[A](property: A => Boolean) extends MySet[A] {
  override def contains(elem: A): Boolean = property(elem) //if the element satisfies the property, the element must be in the set

  //{x in A | property(x)} + element = {x in A | property(x) || x == element}
  override def +(elem: A): MySet[A] = new PropertyBasedSet[A](x => property(x) || x == elem)

  //{x in A | property(x)} ++ set => {x in A | property(x) || set contains x}
  override def ++(anotherSet: MySet[A]): MySet[A] = new PropertyBasedSet[A](x => property(x) || anotherSet(x))

  override def map[B](f: A => B): MySet[B] = politelyFail //Not possible
  override def flatMap[B](f: A => MySet[B]): MySet[B] = politelyFail
  override def foreach(f: A => Unit): Unit = politelyFail

  override def filter(predicate: A => Boolean): MySet[A] = new PropertyBasedSet[A](x => property(x) && predicate(x))
  override def -(elem: A): MySet[A] = filter(x => x != elem)
  override def --(anotherSet: MySet[A]): MySet[A] = filter(!anotherSet)
  override def &(anotherSet: MySet[A]): MySet[A] = filter(anotherSet)
  override def unary_! : MySet[A] = new PropertyBasedSet[A](x => !property(x))

  def politelyFail = throw new IllegalArgumentException("Really deep")
}

object MySet {

  /*
    val mySet = MySet(1, 2, 3) =
    buildSet((1,2,3), []) =
    buildSet((2,3), [] + 1) =
    buildSet((3), [1] + 2) =
    buildSet((), [1, 2] + 3) = [1 + 2 + 3]
  */
  def apply[A](values: A*): MySet[A] = {
    @tailrec
    def buildSet(valSeq: Seq[A], accumulator: MySet[A]): MySet[A] = {
      if (valSeq isEmpty) accumulator
      else buildSet(valSeq.tail, accumulator + valSeq.head)
    }

    buildSet(values.toSeq, new EmptySet[A])
  }
}

object Playground extends App {

  val set = MySet(1, 2, 3, 4)
  val anotherSet = MySet(5, 6, 7)

  set + 5 ++ anotherSet + 3 foreach println

  println

  set.map(_ * 3) // (3, 6, 9, 12)
    .flatMap(x => MySet(x, x * 5)) //(6, 9, 12) ++ MySet(3, 6) = ... = MySet(3, 15, 6, 30, 9, 45, 12, 60)
    .filter(_ % 2 == 0) //MySet(6, 30, 12, 60)
    .foreach(println)

  println

  set - 2 foreach println

  println
  val anotherSet2 = MySet(2, 3)
  set & anotherSet2 foreach println
  println
  set -- anotherSet2 foreach println

  val negative = !set //set.unary_! = all negatives not equal 1,2,3,4
  println(negative(2)) //false
  println(negative(5)) //true

  val negativeEven = negative.filter(_ % 2 == 0)
  println(negativeEven(5)) //false

  val negativeEvenAnd5 = negativeEven + 5
  println(negativeEvenAnd5(5)) //true
}