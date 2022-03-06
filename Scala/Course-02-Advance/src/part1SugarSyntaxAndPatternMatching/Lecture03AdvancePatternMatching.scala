package part1SugarSyntaxAndPatternMatching

object Lecture03AdvancePatternMatching extends App {

  val numbers = List(1, 2, 3, 99)
  val description = numbers match {
    case head :: Nil => println(s"The only element is $head")
    case _ =>
  }

  /*
  - Constants
  - Wildcards
  - Case classes
  - Tuples
  - Some special magic...
  */

  class Person(val name: String, val age: Integer)

  //The unapply function will be invoke to test the pattern and return the value in the match
  object PersonPattern {
    def unapply(person: Person): Option[(String, Int)] =
      if (person.age < 21)
        None
      else
        Some((person.name, person.age))

    def unapply(age: Int): Option[String] =
      Some(
        if (age < 21) "minor"
        else "major"
      )
  }

  val bob = new Person("Bob", 25)

  val greeting = bob match {
    case PersonPattern(n, a) => s"Hi, my name is $n and I am $a yo."
  }
  println(greeting)

  val legalStatus = bob.age match {
    case PersonPattern(status) => s"My legal is $status"
  }
  println(legalStatus)

  /* Exercise */

  val n: Int = 8
  val mathProperty = n match {
    case x if x < 10 => "Single digit"
    case x if x % 2 == 0 => "An even number"
    case _ => "No property"
  }
  println(mathProperty)

  object even {
    def unapply(arg: Int): Boolean = arg % 2 == 0
  }

  object singleDigit {
    def unapply(arg: Int): Boolean = arg < 10
  }

  //if unapply has boolean return type case no need parameters
  val mathPropertyWithPattern = n match {
    case singleDigit() => s"Single digit"
    case even() => s"Even"
  }
  println(mathPropertyWithPattern)


  //Infix patterns - only for case or companions classes with 2 parameters
  case class Or[A, B](a: A, b: B)
  val either = Or(2, "two")
  val humanDescription = either match {
    case number Or string => s"$number is written as $string"
  }

  //Decomposing sequences
  //This works because exist a method called unapplySeq in List object
  val varArg = numbers match {
    case List(1, 2, _*) => "Starting with 1, 2..."
    case _ => "Something else"
  }

  println(varArg)

  //Custom return types for unapply the custom type must define a isEmpty and get
  //isEmpty: Boolean, get: something
  abstract class Wrapper[T] {
    def isEmpty: Boolean
    def get: T
  }

  object PersonWrapper {
    def unapply(person: Person): Wrapper[String] = new Wrapper[String] {
      def isEmpty: Boolean = false
      def get: String = person.name
    }
  }

  val desc = bob match {
    case PersonWrapper(n) => s"This person name is $n"
    case _ => "An alien"
  }

  println(desc)
}
