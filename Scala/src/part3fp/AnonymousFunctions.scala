package part3fp

object AnonymousFunctions extends App {

  val function1 = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 * 2
  }
  
  //Anonymous function or lambda
  val function2: Int => Int = v1 => v1 * 2
  
  //multiple params
  val function3: (Int, Int) => Int = (v1, v2) => v1 + v2

  //no params
  val justDoSomething: () => Int = () => 3

  def giveA3(): Int = 3

  //careful
  println(justDoSomething) //function itself, with classic function works
  println(justDoSomething()) //call

  //curly braces with lambdas
  val stringToInt1 = (str: String) => {
    str.toInt
  }
  val stringToInt2 = { (str: String) =>
    str.toInt
  }

  //MORE syntactic sugar
  val incrementer: Int => Int = (x: Int) => x + 1
  val niceIncrementer: Int => Int = _ + 1
  val adder: (Int, Int) => Int = (v1, v2) => v1 + v2
  val niceAdder: (Int, Int) => Int = _ + _
}
