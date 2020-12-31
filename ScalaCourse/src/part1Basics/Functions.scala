package part1Basics

object Functions extends App {

  //def palabra clave, entre parentesis los parametros con su tipo y la final opcional
  // el tipo devuelto lo puede inferir, tambien se puede hacer en bloque con {}
  //En las funciones recursivas el tipo devuelto es obligatorio
  def aFunction(aParameter: String, anotherParameter: Int): String =
    aParameter + " " + anotherParameter

  aFunction("Pablo", 3)

  //Una funcion sin parametros se puede poner sin ()
  def aParameterLessFunction(): Int = 42

  def anotherParameterLessFunction = 42

  //Se pueden llamar las dos sin parentesis pero si los pones en la definicion te insta para ponerlos en la llamada
  val x = aParameterLessFunction()
  val y = anotherParameterLessFunction

  //Recursion >>>>>>>>> Loops
  def repeatedFunction(string: String, times: Int): String = {
    if (times == 1) string
    else string + repeatedFunction(string, times - 1)
  }

  //Returns Unit
  def functionWithSideEffects(string: String): Unit = print(string)

  def aBigFunction(n: Int): Int = {
    def aSmallFunction(a: Int): Int = a

    aSmallFunction(3)
  }

  def recursiveFactorial(number: Long): Long = {
    if (number < 2) number
    else number * recursiveFactorial(number - 1)
  }

  def recursiveFibonacci(number: Long): Long = {
    if (number <= 2) 1
    else recursiveFibonacci(number - 1) + recursiveFibonacci(number - 2)
  }

  def isPrime(number: Int): Boolean = {
    def isPrimeUntil(n: Int): Boolean =
      if (n <= 1) true
      else number % n != 0 && isPrimeUntil(n - 1)

    isPrimeUntil(number / 2)
  }

  println("Factorial of 5 -> " + recursiveFactorial(5))
  println("Fibonacci of 8 -> " + recursiveFibonacci(8))
  println("Is prime 13 -> " + isPrime(13))
  println("Is prime 8 -> " + isPrime(8))
}
