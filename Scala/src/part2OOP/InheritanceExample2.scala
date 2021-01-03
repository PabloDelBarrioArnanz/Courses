package part2OOP

object InheritanceExample2 extends App {

  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    //Como nuestra lista es +A admite por ejemplo perros y gatos
    //pero se contamina y pasa a ser de un supertipo por ejemplo animal
    def add[B >: A](element: B): MyList[B]
    def printList: String
    override def toString: String = "[" + printList + "]"
  }

  object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
    def printList: String = ""
  }

  class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyList[B] = new Cons(element, this)
    override def printList: String =
      if (t isEmpty) "" + h
      else h + " " + t.printList
  }

  class Father
  class Son extends Father

  val listOfIntegers: MyList[Int] = new Cons[Int](1, new Cons[Int](2, new Cons[Int](3 , Empty)))
  val listOfString: MyList[String] = new Cons[String]("Hello", new Cons[String]("Scala", Empty))
  val list: MyList[Father] = new Cons[Father](new Father, new Cons[Son](new Son, Empty))

  println(listOfIntegers.toString)
  println(listOfString.toString)
  val newList: MyList[Any] = listOfIntegers.add("String") //Lista contaminada
  println(newList)

}
