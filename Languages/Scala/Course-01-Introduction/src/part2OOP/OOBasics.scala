package part2OOP

object OOBasics extends App {

  val person = new Person("Pablo", 23)
  println(person.age)
}

//Constructor
//tambien admite defaults
class Person(name: String = "Temp", val age: Int) {
  //body
  val x = 2
  println(1 + 4)

  //Constructor -- no tiene el tipo que devuelve!
  def this(name: String) = this(name, 0)

  def this() = this("No name")

  def greeting(name: String): Unit = println(s"${this.name} says: Hi $name")

  def greeting(): Unit = println(s"Hi! i am $name")
}

//Los parametros de la clase no son necesariamente campos
//Si añadimos val o var si


//
class Counter(val count: Int) {

  def inc = new Counter(count + 1)
  def inc(n: Int) = new Counter(count + n)

  def dec = new Counter(count - 1)
  def dec(n: Int) = new Counter(count - n)
}