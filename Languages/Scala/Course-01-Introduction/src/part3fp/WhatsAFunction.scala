package part3fp

object WhatsAFunction extends App {

  //DREAM: use functions as first class elements
  //PROBLEM: oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3"))

  val adder: (Int, Int) => Int = (v1: Int, v2: Int) => v1 + v2

  //Function types Function2[A, B, R] === (A, B) => R

  //super function
  val superFunction1: Function1[Int, Function1[Int, Int]] = new Function[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }
  val superFunction2: Int => Int => Int = v1 => v2 => v1 + v2
  val superFunction2Bis = (x: Int) => (y: Int) => x + y

  println(superFunction2(3)(4))
  println(superFunction2Bis(3)(4))
}

trait MyFunction[A, B] {
  def apply(element: A): B
}