package part2OOP

object CaseClass extends App {

  case class Person(name: String, age: Int)

  //1. Todos los atributos del constructor son campos
  val jim = new Person("Jim", 34)
  println(jim.age)

  //2. sensible toString
  println(jim) //Person(Jim, 34)

  //3. equals and hasCode implemented OOTB
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) //True

  //4. Handy copy methods
  val jim3 = jim.copy(age = 45)

  //5. Tienen companion object por defecto, entonces no hace falta el new
  //llama al apply del objeto
  val thePerson = Person
  val mary = Person("Mary", 23)

  //6. Son serializables

  //7. Tienen extractor patterns, se pueden usar en Pattern Matching

  //8. Igual que la case class pero sin la clase companera asociada
  case object UnitedKingdom {
    def name: String = "Something"
  }
}
