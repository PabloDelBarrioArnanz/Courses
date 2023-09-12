package part4Implicits

object OrganizingImplicits extends App {

  //Sorted receives a implicit val witch defines order

  //This implicit val will take precedence from other
  implicit val reverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
  //implicit val normalOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _) doesn't compile 2 possible implicits

  println(List(1,3,5,2,7).sorted)

  /*
    Implicit:
      Types
        - val/var
        - object
        - accessor methods = def with no parentheses
      Scope
        - normal scope = Local scope
        - imported scope
        - companions of all types involved in the method signature
          - List
          - Ordering
          - All the types involved = A or any supertype
  */

  //Exercises
  case class Person(name: String, age: Int)

  val persons = List(
    Person("Steve", 30),
    Person("Amy", 22),
    Person("John", 66)
  )

  //personOrdering is need bcs this wouldn't compile bcs there isn't a implicit Ordering for Person class

  object AgeOrdering {
    implicit val personAgeOrdering: Ordering[Person] = Ordering.fromLessThan((p1, p2) => p1.age < p2.age) //This will take precedence
  }

  object NameOrdering {
    implicit val personNameOrdering: Ordering[Person] = Ordering.fromLessThan((p1, p2) => p1.name < p2.name)
  }

  import AgeOrdering._
  println(persons.sorted)

  /*Exercise
    Add 3 different orders
    - totalPrice most used 50%
    - by unit count = 25%
    - by unit price = 25%
  */
  case class Purchase(nUnit: Int, unitPrice: Double)

  object Purchase {
    implicit val totalPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((p1, p2) =>
      (p1.nUnit * p1.unitPrice) < (p2.nUnit * p2.unitPrice))
  }

  object UnitCountOrdering {
    implicit val unitCountOrdering: Ordering[Purchase] = Ordering.fromLessThan((p1, p2) => p1.nUnit < p2.nUnit)
  }

  object UnitPriceOrdering {
    implicit val unitPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((p1, p2) => p1.unitPrice < p2.unitPrice)
  }

  val purchases = List(
    Purchase(2, 10),
    Purchase(4, 5),
    Purchase(7, 20))

  println(purchases.sorted)

  import UnitPriceOrdering.unitPriceOrdering
  println(purchases.sorted)
}
