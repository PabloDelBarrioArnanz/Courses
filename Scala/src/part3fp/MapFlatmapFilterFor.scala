package part3fp

import scala.collection.immutable

object MapFlatmapFilterFor extends App {

 val list = List(1,2,3)

  println(list.map(_ + 1))
  println(list.filter(_ % 2 == 0))

  val toPair = (x: Int) => List(x, x+1)
  //println(list.flatMap(toPair))
  println(list.flatMap(x => List(x, x+1)))
  println(list.map(x => List(x, x+1)))

  val numbers = List(1,2,3,4)
  val chars = List('a', 'b','c','d')
  val colors = List("Black", "White")

  //Double for
  println(numbers.flatMap(x => chars.map(y => "" + y + x)))
  //Triple for
  println(numbers.flatMap(x => chars.flatMap(y => colors.map(z => "" + z + y + x))))

  //for-comprehensions
 //Itera todas las listas  y se las puede añadir operaciones
  val forCombination = for {
    x <- numbers.filter(_ % 2 == 0)
    y <- chars if y == 'a'
    z <- colors.map("Color " + _)
  } yield "" + x + y + z
 println(forCombination)

 for {
  x <- List(1,2,3,4,5,6,7,8,9)
  y <- List(1,2,3,4,5,6,7,8,9)
 } println(x + "x" + y + "=" + x*y)

 //Check Maybe
}
