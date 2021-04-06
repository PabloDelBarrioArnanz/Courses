package part4pm

import scala.util.Random

object PatternMatching extends App {

  //switch with steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else"
  }

  //1. Decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi my name is $n and I can't drink in US"
    case Person(n, a) => s"Hi my name is $n and I am $a years old"
    case _ => "I don't know who i am"
  }
  println(greeting)

  /*1. cases are matched in order
    2. will throw an error if there isn't a default value
    3. the type will be the unified of all cases
    4. PM works really well with case classes
   */

  //PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBread) => println(s"Matched a dog of the $someBread")
  }

  //match everything
  val isEvenNormal = x % 2 == 0
  val isEvenCond = if (x % 2 == 0) true else false
  val isEven = x match {
    case n => n % 2 == 0
    case _ => false
  }

  trait Expr
  case class Number(n: Int) extends  Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Prod(e1, e2) =>
      def maybeShowParenthesis(expr: Expr) = expr match {
        case Prod(_, _) => show(expr)
        case Number(_) => show(expr)
        case _ => "(" + show(expr) + ")"
      }
      maybeShowParenthesis(e1) + " * " + maybeShowParenthesis(e2)
  }
  println(show(Sum(Number(2), Sum(Prod(Number(3), Number(4)), Number(3)))))
  println(show(Prod(Number(2), Sum(Prod(Number(3), Number(4)), Number(3)))))
}
