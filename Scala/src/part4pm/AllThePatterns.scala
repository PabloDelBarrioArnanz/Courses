package part4pm

import part2OOP.InheritanceExample4.{MyList, Empty, Cons}

object AllThePatterns extends App {

  val x: Any = "Scala"

  val constant = x match {
    case 1 => "a number"
    case "Scala" => "THE scala"
    case true => "THE TRUTH"
    case AllThePatterns => "A singleton object"
    case _ =>
  }

  // 2 - Match anything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ =>
  }

  //2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
    case _ =>
  }

  // 3 Tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) => //...//
    case (something, 2) => s"I've found $something"
    case _ =>
  }

  //nested tuple
  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) =>
    case _ =>
  }

  // 4 - case classes called -> constructor pattern
  //PMs can be nested with CCs as well
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty => //...//
    case Cons(head, Cons(subHead, subTail)) => //...//
    case _ =>
  }

  // 5 - list patterns
  val aStandardList = List(1, 2, 3, 42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _, _) => //extractor advance
    case List(1, _*) => //list of arbitrary length
    case 1 :: List(_) => //infix pattern
    case List(1, 2, 3) :+ 42 => //infix pattern
    case _ =>
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => //explicit type specifier
    case _ =>
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case nonEmptyList@Cons(_, _) => //name binding => use the name later (here)
    case Cons(1, rest@Cons(2, _)) =>
    case _ =>
  }

  // 8 - multi-patterns
  val multiPattern = aList match {
    case Empty | Cons(0, _) => //Compound or multi-pattern
    case _ =>
  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
    case _ =>
  }

  /////////////////////////////////////

  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case _: List[String] => "a list of strings"
    case _: List[Int] => "a list of numbers"
    case _ => ""
  }
  println(numbersMatch) //A list of strings -> JVM :( al introducir la retrocompatibilidad de Java5 con los genéricos hicieron que solo comprobara la clase y no la clase y el tipo
}
