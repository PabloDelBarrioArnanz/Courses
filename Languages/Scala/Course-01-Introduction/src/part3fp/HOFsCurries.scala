package part3fp

import scala.annotation.tailrec

object HOFsCurries extends App {

  //HOF, map, flatMap, filter has a function as parameter
  val superFunction: (Int, (String, Int => Boolean) => Int) => Int => Int = null

  //Function that applies a function n times over a value x
  //nTimes(f, n, x)
  //nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x))
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  //increment10 - ntb(plusOne, 10) = x => plusOne(plusOne...(x))
  def nTimesBetter(f: Int => Int, n: Int): Int => Int =
    if (n <= 0) x => x
    else x => nTimesBetter(f, n - 1)(f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  //curried functions
  val supperAdder: Int => Int => Int = x => y => x + y
  val add3 = supperAdder(3) // y = y + 3
  println(add3(10))
  println(supperAdder(3)(10))

  //function with multiples parameter list
  def curriedFormatter(c: String) (x: Double): String = c.format(x)

  val standardFormat: Double => String = curriedFormatter("%4.2f")
  val preciseFormat: Double => String = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
}
