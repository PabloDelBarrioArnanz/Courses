package part2OOP

object Exceptions extends App {

  val x: String = null
  //println(x.length)

  //val aWeirdValue = throw new NullPointerException

  //Siempre su ejecuta el finally, aunque se lance un excepcion que no este capturada
  //es opcional
  //No afecta al tipo de retorno
  //se puede usar para side effects
  val potentialFail: Unit = try {
    throw new RuntimeException("")
  } catch {
    case e: RuntimeException => println("Caught a RuntimeException")
  } finally println("Finally")

  val potentialFail2: Any = try {
    throw new RuntimeException("")
  } catch {
    case e: RuntimeException => println("Caught a RuntimeException")
    case f: NullPointerException => 23
  } finally println("Finally")

  val potentialFail3: Int = try {
    throw new RuntimeException("")
  } catch {
    case f: NullPointerException => 23
  } finally println("Finally")

  //Excepciones Propias

  class MyException extends Exception

  val exception = new MyException
  //throw exception
}
