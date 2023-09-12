package part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  // well defined order
  //can be indexed
  //Supports: apply, iterator, length, reverse, concatenation, appending, prepending, grouping, sorting...

  val aSequence = Seq(1,2,3,4)
  println(aSequence)
  println(aSequence.isEmpty)
  println(aSequence.reverse)
  println(aSequence(2)) //The third element
  println(aSequence ++ Seq(5,6,7))
  println(aSequence.sorted)

  //ranges
  val aRange: Seq[Int] = 1 to 10
  println(aRange) //Range 1 to 10
  aRange.foreach(println)

  (1  to 10).foreach(x => println("Hello"))

  //LinearSeq -> Immutable LinkedList
  //methods like head, tail, isEmpty are O(1)
  //most of the operations are O(n) length, reverse

  val aList = List(1,2,3)
  val prePended1 = 23 :: aList //Can be a number or a List
  val prePended2 = 23 +: aList :+ 12

  val apple5 = List.fill(5)("Apple")
  println(apple5.mkString("<->"))


  //Array
  //Similar to Java
  //Can be manually constructed with a predefined size
  //can be muted
  //Indexing is fast
  //are interoperable with java

  val anArray = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements.mkString(",")) //Default values to 0

  anArray(2) = 0 //syntax sugar for anArray.update(2, 0)
  val numberSeq: Seq[Int] = anArray //implicit conversion
  println(numberSeq) //SpecialType WrappedArray

  //Vector
  //Default impl for immutable seq
  //indexed read and write constant O(log32(n))
  //good performance with large sizes
  //fast element addition
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  //vectors vs list
  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random()
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  //keeps references to tail
  //updating an element in the middle takes long
  println(getWriteTime(numbersList))
  //depth of the tree is small
  //needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))
}
