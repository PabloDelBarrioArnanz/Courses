package part4pm

object PatternsEverywhere extends App {

  //big idea #1
  //catches are actually MATCHES
  try {
    //code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "Something else"
  }

  //big idea #2
  //generators are also based on PM
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0
  } yield 10 * x

  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second

  //big idea #3
  //multiple value definitions based on PM
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  println(b) // 2

  val head :: tail = list
  println(head)
  println(tail)

  //big idea #4
  //partial function based on PM
  val mappedList1 = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "something"
  } //partial function literal

  val mappedList2 = list.map { x => x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case _ => "something"
    }
  }
}
