package part2OOP

object InheritanceExample1 extends App {

  abstract class MyList {
    def head: Int
    def tail: MyList
    def isEmpty: Boolean
    def add(element: Int): MyList
    def printList: String
    override def toString: String = "[" + printList + "]"
  }

  object Empty extends MyList {
    def head: Int = throw new NoSuchElementException
    def tail: MyList = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add(element: Int): MyList = new Cons(element, Empty)
    def printList: String = ""
  }

  class Cons(h: Int, t: MyList) extends MyList {
    def head: Int = h
    def tail: MyList = t
    def isEmpty: Boolean = false
    def add(element: Int): MyList = new Cons(element, this)
    override def printList: String =
      if (t isEmpty) "" + h
      else h + " " + t.printList
  }

  val list: Cons = new Cons (1, new Cons(2, new Cons(3, Empty)))
  println(list.head)
  println(list.tail)
  println(list.tail.tail)
  val newList = list.add(4)
  println(newList.isEmpty)
  println(newList)
}
