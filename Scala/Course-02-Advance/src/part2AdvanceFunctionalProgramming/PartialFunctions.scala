package part2AdvanceFunctionalProgramming

object PartialFunctions extends App {

  // PF are function which only takes parameter from a subdomain
  val aFunction = (x: Int) => x + 1 // Function1[Int, Int] === Int => Int

  val aFussyFunction = (x: Int) =>
    if (x == 1) 42
    else if (x == 2) 56
    else if (x == 3) 999
    else throw new FunctionNotApplicableException

  class FunctionNotApplicableException extends RuntimeException

  val aNiceFussyFunction = (x: Int) => x match {
    case 1 => 42
    case 2 => 56
    case 3 => 999
  }
  //{1, 3, 5} => Int

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 3 => 999
  } //partial function value

  println(aPartialFunction(2))
  //println(aPartialFunction(26)) Matching error

  //PF utilities
  println(aPartialFunction.isDefinedAt(67)) //false

  val lifted = aPartialFunction.lift //Int => Option[Int]
  println(lifted(2)) //Some(42)
  println(lifted(289)) //None

  val pfChain = aPartialFunction.orElse[Int, Int] {
    case 45 => 67
  }

  println(pfChain(2)) //56
  println(pfChain(45)) //67

  //PF extends normal functions

  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  //HOF accept partial functions as well

  val aMappedList = List(1, 2, 3).map {
    case 1 => 42
    case 2 => 78
    case 3 => 1000
  }

  //PF can only have ONE parameter type

  /*Exercises

   1. Construct a PF instance yourself f(anonymous class)
   2. Dumb chat bot as a PF
  */
  //To build a manual PF you need to override apply and isDefinedAt functions
  val aManualFussyFunction = new PartialFunction[Int, Int] {
    override def apply(x: Int): Int = x match {
      case 1 => 42
      case 2 => 65
    }

    override def isDefinedAt(x: Int): Boolean = x == 1 | x == 2
  }

  val chatbot: PartialFunction[String ,String] = {
    case "hello" => "Hi my name is HAL9000"
    case "goodbye" => "Once you start talking to me, there is no return"
  }

  scala.io.Source.stdin.getLines().map(chatbot).foreach(println)

  /*
    An examples
    trait Seq[+A] extends PartialFunction[Int, A] {
      def apply(index: Int): A --> function apply from Seq returns the element in position indicated in the parameter
      this function only works for (0, length -1) so its a partial function, Seq are PartialFunctions
    }

    trait Map[A, +B] extends PartialFunction[A, B] {
      def apply(key: A): B
    }
    Map its defined on the domain of its keys
  */
}
