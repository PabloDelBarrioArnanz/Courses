package part1Basics

object Expressions extends App {

  val x = 1 + 2 //Expression
  print(x)

  //Operators
  // + - * /    & | ^ << >>    >>> (exclusivo de scala right shift with zero)

  // == != < > <= >=
  println(1 == x) //false
  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // -= *= /*

  // Instructions (Do something) vs Expressions (Value)

  // IF Expression -> devuelve un valor no hace falta hacer instrucciones
  val aCondition = true
  val aConditionedValue = if (aCondition) 5 else 3
  println(aConditionedValue) //5

  //Dont use loops xD
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

  val aWeiredValue: Unit = (aVariable = 3) //Esta expresion devuelve Unit
  print(aWeiredValue) // ()

  //Code Blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z < 2 ) "Hello" else "Goodbye"
  }
  //val anotherVal = y + 1 no visible fuera del bloque
}
