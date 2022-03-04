package part2OOP

//Syntactic sugar
object MethodNotation extends App {

  class Person(val name: String, favouriteMovie: String) {
    def likes(movie: String): Boolean = movie == favouriteMovie

    def hangOutWith(person: Person): String = s"$name is hanging out whit ${person.name}"

    def +(person: Person): String = s"$name is hanging out whit ${person.name}"

    def unary_! : String = s"$name what the heck?"

    def isAlive: Boolean = true

    def apply(): String = s"Hi! my name us $name and I like $favouriteMovie"
  }

  val mary = new Person("Marry", "Inception")
  //infix notation - solo funciona con metodos que tienen 1 paremetro
  println(mary.likes("Inception"))
  println(mary likes "Inception") //Equivalent

  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  //Se puede llamar a los metodos como operaciones
  println(mary + tom)
  println(mary.+(tom))
  println(1.+(2)) //Es lo mismo porque todos los operadores son func

  //Prefix notation tienen que llamarse unary_ y - + ! ~
  val x = -1
  val y = 1.unary_- //equivalent
  println(!mary)

  //Postfix notation solo parametodos sin paremetros
  println(mary isAlive)

  //apply la funcion apply se puede llamar solo con el objeto y los parentesis
  //como un constructor vacio
  println(mary.apply())
  println(mary())
}
