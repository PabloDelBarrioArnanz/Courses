package part1SugarSyntaxAndPatternMatching

import scala.annotation.tailrec
import scala.util.Try

object Lecture02DarkSyntaxSugar extends App {

  //Syntax sugar #1: methods with single param
  def singleArgMethod(arg: Int): String = s"$arg little ducks..."

  val description = singleArgMethod {
    //code code code
    42
  }

  println(description) //42 little ducks..."

  val aTryInstance = Try { //java's try

  }

  List(1,2,3).map { x =>
    x + 1
  }


  //Syntax sugar #2: single abstract method
  trait Action {
    def act(x: Int): Int
  }

  val anInstance1: Action = new Action {
    override def act(x: Int): Int = x + 1
  }

  val anInstance2: Action = (x: Int) => x + 1  //magic

  //example: Runnables

  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("Hello")
  })

  val anOtherThread = new Thread(() => println("Hello"))

  //can have multiple unimplemented methods but only 1 unimplemented
  abstract class AnAbstractType {
    def implement: Int = 23
    def f(a: Int): Unit
  }

  val anAbstractInstance: AnAbstractType = (a: Int) => println("Sweet!")


  //Syntax sugar #3: the :: and #:: methods are special
  //right associative if ending in :
  //left associative otherwise
  val prependedList = 1 :: List(2,3)
  println(prependedList) //1,2,3


  //Syntax sugar #4: multi-word method naming
  class Girl(name: String) {
    def `and then said`(gossip: String): Unit = println(s"$name said $gossip")
  }

  val lilly = new Girl("Lilly")
  lilly `and then said`("Scala is so sweet!")


  //Syntax sugar #5: infix types
  class Composite[A, B]
  val composite1: Composite[Int, String] = null
  val composite2: Int Composite String = null

  class -->[A, B]
  val towards: Int --> String = null


  //Syntax sugar #6: update() is very special, much like apply()
  val  anArray = Array(1,2,3)
  anArray(2) = 7 //rewrite to anArray.update(2, 7) in position 2 put number 7
  println(anArray.mkString("Array(", ", ", ")"))

  //Syntax sugar #7: setters for mutable containers
  class Mutable {
    private var internalMember: Int = 0
    def member: Int = internalMember //getter
    def member_=(value: Int): Unit =
      internalMember = value //setter
  }

  val aMutableContainer = new Mutable
  aMutableContainer.member = 42 //rewritten as aMutableContainer.member_=(42)
}
