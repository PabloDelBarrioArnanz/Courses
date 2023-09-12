package part1SugarSyntaxAndPatternMatching

import scala.annotation.tailrec

object Lecture01Recap extends App {

  val aCondition: Boolean = false //val variableName: typeOfData = value
  val aConditional = if (aCondition) 42 else 21

  //instructions vs expressions
  val aCodeBlock = {
    if (aCondition) 42 else 21
  }

  //unit = void
  val theUnit: Unit = println("Hello!")

  //functions
  def aFunction(x: Int): Int = x + 1

  //recursion stack and tail
  @tailrec
  def factorial(n: Int, accumulator: Int): Int =
    if (n <= 0) accumulator
    else factorial(n-1, n * accumulator)

  //object-oriented programming

  class Animal
  class Dog extends Animal
  val aDog: Animal = new Dog //subtyping polymorphism

  //traits
  trait Carnivore {
    def eat(a: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def eat(a: Animal): Unit = println("Crunch!")
  }

  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog //natural language

  //1 + 2
  //1 .+(2)

  //Anonymous Classes
  val aCarnivore = new Carnivore {
    override def eat(a: Animal): Unit = println("Roar!")
  }

  //Generics
  abstract class MyList[+A] //variance and covariance

  //Singleton and companions
  object MyList

  //Case Classes
  case class Person(name: String, age: Int)

  //Exceptions
  //val throwsExceptions = throw new RuntimeException
  val aPotentialFailure = try {
    throw new RuntimeException
  } catch {
    case e: Exception => "I caught an exception"
  } finally {
    println("Some logs")
  }

  //Functional programming
  val incrementer1 = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }
  val incrementer2 = (v1: Int) => v1 + 1

  //map, flatmap, filter...
  val result = List(1,2,3).map(incrementer2) //High order function
  println(result)

  //for-comprehension
  val pair1 = for {
    num <- List(1,2,3)
    char <- List('a','b','c')
  } yield num + "-" + char

  val pair2 = List(1,2,3).flatMap(num => List('a','b','c').map(letter => num + "-" + letter))

  println(pair1)
  println(pair2)

  //Maps, Sequences, Arrays, List, Vectors, Tuples...
  val aMap = Map(
    "Pablo" -> 123,
    "Laura" -> 321
  )

  //Options and Try
  val anOption = Some(2)

  //Pattern Matching
  val x = 2
  val order = x match {
    case 1 => "First"
    case 2 => "Second"
    case 3 => "Third"
    case _ => x + "Th"
  }

  val bob = Person("Bob", 22)
  val greeting = bob match {
    case Person(n, _) => s"Hi, my name is $n"
  }

  //all the patterns
}
