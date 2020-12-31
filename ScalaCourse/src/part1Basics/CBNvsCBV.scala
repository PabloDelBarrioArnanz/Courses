package part1Basics

object CBNvsCBV extends App {

  //Recibe un valor
  def callByValue(x: Long): Unit = {
    println("by Value: " + x)
    println("by Value: " + x)
  }

  //Recibe la funcion sin ejecutar y hasta que no lo requiere no la ejecuta
  def callByName(x: => Long): Unit = {
    println("by Name: " + x)
    println("by Name: " + x)
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int): Unit = print(x)

  //printFirst(infinite(), 34) //Crash overflow
  printFirst(34, infinite()) //Nada, porque la funcion infinito nunca se llega a usar
}
