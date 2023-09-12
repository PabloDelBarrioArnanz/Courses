package part4Implicits

object ImplicitsIntro extends App {

  val pair = "Daniel" -> "123"
  val intPair = 1 -> 2


  case class Person(name: String) {
    def greet = s"Hi my name is $name!"
  }

  implicit def fromStringToPerson(str: String): Person = Person(str)

  println("Peter".greet) //println(fromStringToPerson("Peter").greet)

  /*
  Error bcs compiler doesn't know witch greet method you refers
  class A {
    def greet: Int = 2
  }

  implicit def fromStringToA(str: String): A = new A
  */

  //implicit parameter NOT default args
  //Implicits no need specifies second parameter as empty, the compiler will add it with the value of the implicit val
  def incrementImplicitParameter(x: Int)(implicit amount: Int): Int = x + amount
  def incrementDefaultParameter(x: Int)(amount2: Int = 10): Int = x + amount2

  implicit val defaultAmount: Int = 10

  println(incrementImplicitParameter(2)) //12
  println(incrementImplicitParameter(2)(2)) //4

  println(incrementDefaultParameter(2)()) //12
  println(incrementDefaultParameter(2)(2)) //4

  //This is like Futures works, second parameter its ExecutionContext but if you don't specifies it, compiler will add the implicit value
}
