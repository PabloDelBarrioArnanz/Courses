package part4Implicits

import scala.util.Try

object PimpMyLibrary extends App {

  //2.isPrime

  //implicit class only takes 1 argument
  implicit class RichInt(val value: Int) extends AnyVal { //extends AnyVal is for memory optimization
    def isEven: Boolean = value % 2 == 0

    def sqrt: Double = Math.sqrt(value)
  }

  implicit class RicherInt(richInt: RichInt) {
    def isOdd: Boolean = richInt.value % 2 != 0
  }

  new RichInt(12).isEven
  new RichInt(12).sqrt

  42.isEven //implicit search -> compiler automatic transform it to new RichInt(42) bcs exist a implicit class with accepts a int and has a isEvent method
  //54.isOdd doesn't compile, compiler can't transform 42 into RichInt and then into RicherInt

  //type enrichment = pimping

  1 to 10 //to is a function from RichInt

  import scala.concurrent.duration._

  3.seconds //implicit method

  /*
    Exercises
      1. Enrich the String class
        - asInt
        - encrypt
      2. Make function
        - 3.times (() => ..)
        * 2 * List(1,2) = List(1,2,1,2,1,2)
  */

  //1.
  implicit class RichString(val value: String) extends AnyVal {

    def asInt: Int = Integer.valueOf(value)

    def encrypt(distance: Int): String = value.map(character => (character + distance).asInstanceOf[Char])
  }

  println("34".asInt)
  println("Hola".encrypt(2))

  //2.
  implicit class SuperInt(value: Int) extends AnyVal {

    def times(f: () => Unit): Unit =
      if (value >= 0) {
        f()
        (value - 1).times(f)
      }

    def *(toRepeat: String): String =
      if (value > 1) toRepeat + (value - 1).*(toRepeat)
      else toRepeat

    def *[A](list: List[A]): List[A] =
      if (value > 1) list ++ (value - 1).*(list)
      else list

  }

  println(2.times(() => print("Hola")))

  println(3 * "Hola")
  println(3 * List(1,2))


  //DANGER ZONE
  //"3" / 4 it's possible make JS behaviour with implicits

  //implicit def stringToInt(string: String): Int = Integer.valueOf(string)
  //println("3" / 4)

}
