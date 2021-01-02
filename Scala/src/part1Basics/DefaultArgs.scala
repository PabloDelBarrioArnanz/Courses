package part1Basics

import scala.annotation.tailrec

object DefaultArgs extends App {

  @tailrec
  def trFactorial(number: Long, accumulator: Long = 1): Long =
    if (number < 2) accumulator
    else trFactorial(number - 1, number * accumulator)

  //Son lo mismo
  var fact10 = trFactorial(10, 1)
  fact10 = trFactorial(10)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("Saving picture")

  savePicture()
  //savePicture(800) falla no sabe que paremetro queremos sobre escribir
  savePicture(width = 800) // indicamos el parametro
}
