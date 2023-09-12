package part2OOP

object InheritanceExample3 {

  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): MyList[B]
    def map[B](transformer: MyTransformer[A, B]): MyList[B]
    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
    def filter(predicate: MyPredicate[A]): MyList[A]
    def ++[B >: A](list: MyList[B]): MyList[B]
    def printList: String
    override def toString: String = "[" + printList + "]"
  }

  object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
    def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
    def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
    def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
    def printList: String = ""
  }

  class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyList[B] = new Cons(element, this)

    def filter(predicate: MyPredicate[A]): MyList[A] =
      if (predicate.test(h)) new Cons(h, t.filter(predicate))
      else t.filter(predicate)

    def map[B](transformer: MyTransformer[A, B]): MyList[B] =
      new Cons(transformer.transform(h), t.map(transformer))

    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
      transformer.transform(h) ++ t.flatMap(transformer)

    def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
    def printList: String =
      if (t isEmpty) "" + h
      else h + " " + t.printList
  }

  trait MyPredicate[-T] {
    def test(elem: T): Boolean
  }

  trait MyTransformer[-A, B] {
    def transform(elem: A): B
  }


  def main(args: Array[String]): Unit = {
    val listOfIntegers: MyList[Int] = new Cons[Int](1, new Cons[Int](2, new Cons[Int](3 , Empty)))
    val anotherListOfIntegers: MyList[Int] = new Cons[Int](1, new Cons[Int](2, new Cons[Int](3 , Empty)))
    listOfIntegers.map(new MyTransformer[Int, Int] {
      override def transform(elem: Int): Int = elem * 2
    })
    listOfIntegers.filter(new MyPredicate[Int] {
      override def test(elem: Int): Boolean = elem % 2 == 0
    })
    println(listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
      override def transform(elem: Int): MyList[Int] = new Cons(elem, new Cons[Int](elem + 1, Empty))
    }))
  }
}
