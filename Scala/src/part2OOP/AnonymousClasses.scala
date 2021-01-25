package part2OOP

object AnonymousClasses extends App {

  abstract class Animal {
    def eat(): Unit
  }

  val funnyAnimal1: Animal = new Animal {
    override def eat(): Unit = println("hahahahahahah")
  }
  val funnyAnimal2: Animal = () => println("hahahahahahah")

  class Person(name: String) {
    def this() = this("")
    def sayHi(): Unit = println(s"Hi my name is $name")
  }

  abstract class AbstractPerson(name: String) {
    def this() = this("")
    def sayHi(): Unit = println(s"Hi my name is $name")
    def test(): Unit = println(s"Hi my name is $name")
  }

  val jim: Person = new Person("Jim") {
    override def sayHi(): Unit = println("Hello i am Jim")
  }
}
