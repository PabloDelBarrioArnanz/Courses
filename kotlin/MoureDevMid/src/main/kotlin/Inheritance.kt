fun main() {

    val programmer = Programmer("Pablo", 26, "Kotlin")
    println("El programador ${programmer.name}")
    programmer.work()

    val designer = Designer("Not Pablo", 29, "Open concept")
    designer.work()

}


// Todas las clases y métodos son finales a no ser que las hagamos abiertas "open" y así podemos extender o sobreescribir
//Cualquier clase de kotlin hereda de la superclase "Any" como en java de "Object"
open class Person(
    private val name: String,
    age: Int
) : Work(), Vehicle { // extends Work implements Vehicle

    open fun work() {
        println("Esta persona está trabajando")
    }

    override fun goToWork() {
        println("Voy al trabajo")
    }

}

// Si no tiene val o var no se podrá usar en nuestra clase ni fuera
class Programmer(val name: String, age: Int, private val favouriteLanguage: String) :
    Person(name, age) { // calling "super" de java
    override fun work() {
        println("Esta persona está programando en $favouriteLanguage")
    }

    override fun goToWork() {
        println("Trabajo en remoto")
    }
}

class Designer(name: String, age: Int, favouriteDesign: String) : Person(name, age) { // calling "super" de java
    override fun work() {
        super.work()
        println("Esta persona está diseñando")
    }
}

// Las clases abstractas son open
abstract class Work {
    abstract fun goToWork()
}

interface Vehicle