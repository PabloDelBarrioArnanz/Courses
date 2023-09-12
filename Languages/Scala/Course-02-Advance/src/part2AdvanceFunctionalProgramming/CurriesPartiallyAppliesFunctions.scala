package part2AdvanceFunctionalProgramming

object CurriesPartiallyAppliesFunctions extends App {

  //Currie function its a function witch returns an other function as result
  val superAdder: Int => Int => Int = {
    x => y => x + y
  }

  val add3 = superAdder(3)
  println(add3(5))
  println(superAdder(3)(5))

  def curriedAdder(x: Int)(y: Int): Int = x + y //curried method

  //lifting = ETA-EXPANSION
  val add4 = curriedAdder(4)

  //functions != methods (JVM limitation)  method are instances from classes no from functions
  //lifting = ETA-EXPANSION technique to wrapping functions into a extra layer while preserving identical functionality
  //performed by the compiler to create function out of methods

  def inc(x: Int): Int = x + 1
  List(1,2,3).map(x => inc(x)) //ETA-EXPANSION inc method to function

  //Partial Functions Applications
  val add5 = curriedAdder(5) _ //tell compiler make a function Int => Int

  val simpleAddFunction = (x: Int, y: Int) => x + y
  def simpleMethod(x: Int, y: Int) = x + y
  def curriedMethod(x: Int)(y: Int) = x + y

  //add7: Int => Int = y => 7 + y
  val add7_1 = (x: Int) => simpleAddFunction(7, x)
  val add7_2 = (x: Int) => simpleAddFunction.curried(7)
  val add7_3 = (x: Int) => curriedMethod(7) _ //PAF
  val add7_4 = (x: Int) => curriedMethod(7)(_) //PAF
  val add7_5 = (x: Int) => simpleMethod(7, _: Int) //PAF alternative syntax for turning methods into functions values
  //                      y => simpleMethod(7, y)
  val add_6 = (x: Int) => simpleAddFunction(7, _: Int)


  //Example
  def concatenator(a: String, b: String, c: String) = a + b + c
  val insertName = concatenator("Hello, I'am ", _: String, ", how are you?") //x: String => concatenator("hello...", x , "how are..")
  println(insertName("Pablo"))

  val fillInTheBlanks = concatenator("Hello, ", _: String, _: String)
  println(fillInTheBlanks("Pablo", " Scala is awesome"))

  //Exercises
  /*
    1. Process a list of numbers and return their string representation with different formats
      Use the %4.2f, %8.6f and %14.12f with a curried formatter function
  */
  println
  val number = 3214.1232
  def formatter(format: String)(number: Number) = format.format(number)

  val formatter42 = formatter("%4.2f") _
  println(formatter42(number))

  val formatter86 = number => formatter("%8.6f")(number)
  println(formatter86(number))


  /*
    2. Differences between
      - functions vs methods
      - parameter: by-name vs 0-lambda
  */
  println
  def byName(n: => Int) = n + 1
  def byFunction(f: () => Int) = f() + 1

  def method: Int = 42
  def parenMethod(): Int = 42

  byName(23) //ok
  byName(method) //ok
  byName(parenMethod()) //ok
  //byName(parenMethod) //Scala 3 not permitted

 // byName(() => 42) not ok
 byName((() => 42)()) //ok
 //byName(parenMethod _) no ok

 //byFunction(45) not ok
 //byFunction(method) not ok because will be evaluated
 //byFunction(parenMethod()) no ok
 byFunction(parenMethod) //ok does ETA-Expansion
 byFunction(parenMethod _) //ok
 byFunction(() => 46) //ok

}
