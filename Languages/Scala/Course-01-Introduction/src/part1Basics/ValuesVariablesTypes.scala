package part1Basics

object ValuesVariablesTypes extends App {

  //Val es constante y var no
  val x: Int = 42
  //x = 2 No es posible porque val es inmutable
  val p = 42 // Sigue funcionando porque infiere que es int

  val aString: String = "Hello"
  val anotherString = "Goodbye"
  val aBoolean: Boolean = true
  val anotherBoolean = false
  val aChar: Char = 'a'
  val aShort: Short = 234
  val aLong: Long = 2342323427878L
  val aDouble: Double = 2.0
  val aFloat: Float = 2.0f

  //variables
  var aVariable: Int = 4
  aVariable = 5
}
