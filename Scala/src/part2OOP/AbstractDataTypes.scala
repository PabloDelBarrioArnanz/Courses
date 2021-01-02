package part2OOP

object AbstractDataTypes extends App {

  abstract class Animal {
    val creatureType: String

    def eat(): Unit
  }

  //val animal = new Animal No se puede porque es Abstracta
  //Te obliga a sobreescribir los metodos de la clase abstracta
  //En este caso el override es opcional no como si fuera herencia normal
  class Dog extends Animal {
    override val creatureType: String = "Canine"

    def eat(): Unit = println("Crunch crunch")
  }

  //Traits no pueden tener parametros de constructor
  //pueden tener partes implementadas y partes abstractas
  //Se puede extender una clase pero muchos traits
  //trait definen comportamiento y abstract cualidades
  //Un trait no puede usar (with) otro trait
  //Un trait no tiene porque implementar los metodos abtractos de las clases que hereda
  trait Carnivore extends Animal {
    val preferredMeal: String = "Meat"
    def eat(animal: Animal): Unit
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "Croc"

    override def eat(): Unit = println("nomnomnom " + preferredMeal)

    override def eat(animal: Animal): Unit = println(s"I am a croc and i am eating a ${animal.creatureType}")
  }

  val dog = new Dog
  val crocodile = new Crocodile
  crocodile.eat(dog)


  //Scala.Any madre de todos los tipos
      //Scala.AnyVal -> Int, Boolean, Float
      //Scala.AnyRef -> todas las clases mapeadas de java aunque no lo pongas to-do hereda de any red
        //Scala.Null -> Sustituye a todos los de arriba
  //scala.Nothing -> Sustituye to-do
}
