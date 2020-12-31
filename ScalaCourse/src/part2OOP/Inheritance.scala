package part2OOP

object Inheritance {

  sealed class Animal {
    val creatureType = "wild"
    protected def eat(): Unit = println("ñamñamñam")
  }

  class Cat extends Animal {
    def crunch(): Unit = eat()
  }

  val cat = new Cat
  //cat.eat() Fail protected
  cat.crunch()

  //Constructors

  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)
  //O extends Person(name, age) si no tuviese el segudno constructor la clase person

  //overrides es obligatorio
  //Se pueden poner en el constructor de la clase hija los parametros que queremos
  //que se sobreescriban
  //super para referirnos a cosa del padre
  class Dog(override val creatureType: String) extends Animal {
    //override val creatureType: String = "Domestic"
    override def eat(): Unit = {
      super.eat()
      println("Crunch crunch")
    }
  }
  val dog = new Dog("K9")


  //Type substitution, polymorphism
  val unknownAnimal: Animal = new Dog("Test")
  unknownAnimal.creatureType

  //preventing overriding
  //1 final - en la variable/metodo
  //2 final - en la clase entera no se pude ni extender
  //3 sealed - seal the class = extend classes in this file, no en otros!
}
