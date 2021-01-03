package part2OOP

object Generics extends App {

  class MyList[+A] {
    //Use the type A
    def add[B >: A](element: B): MyList[B] = null
  }

  object MyList {
    def emptyList[A]: MyList[A] = null
  }

  val listOfInteger = new MyList[Int]
  val listOfString = new MyList[String]
  val emptyListOfInteger = MyList.emptyList[Int]

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  /*
   * Lista covariante pide [+A] le vale A y to-do lo que herede de A -> permite ser contaminada
   * Lista invariante [A] solo le vale A nada de lo que herede A ni lo que
   * List contravariante [-A] le vale A y cualquier cosa por encima
   */

  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Animal]

  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  //Bounded types
  //La clase caja1 permite animal y subtipos de animal
  class Cage1[A <: Animal](animal: A)
  new Cage1(new Dog)
  //La clase caja2 permite animal y supertipos de animal
  class Cage2[A >: Animal](animal: A)

}
