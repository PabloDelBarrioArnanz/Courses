package part2OOP

import part2OOP.Objects.Person.N_EYES

object Objects {

  //Scala no tiene funcionalidad a nivel de clase, no hay static
  //Tiene objetos - singleton instance

  object Person {
    //static/class level functionality
    private val N_EYES = 2
    def canFly: Boolean = false

    //Metodo factory suele usarse el nombre apply
    //De esta forma al llamarlo solo con () es como usar un constructor tipico pero
    //En realidad es un funcion en el objeto
    def apply(mother: Person, father: Person): Person = new Person
  }

  class Person {
    N_EYES
    //instance level functionality
  }

  class NotPerson {
    //N_EYES -- Fail
  }
  //Una clase y un objeto que se llaman igual son compañeros y por lo tanto
  //se pueden acceder a sus constantes y metodos aunque sean privados

  //Scala Applications = Scala object with
  //def main(args: Array[String]): Unit
  //lo mismo que usar el extends App
  def main(args: Array[String]): Unit = {
  //println(Person.N_EYES) -- Fail
  println(Person canFly)

  val person1 = Person
  val person2 = Person
  println(person1 == person2) //True

  val mary = new Person
  val john = new Person
  println(mary == john) //False

  val bobbie = Person(mary, john)
  }
}
