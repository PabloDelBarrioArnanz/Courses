// Existen 4 modificadores private, protected, internal y public
// Por defecto siempre es público a diferencia de java que es protected y public para interfaces
// Las clases aunque sean privadas, se pueden acceder a ella construstror dentro del mismo fichero pero no fuera, a los atributos nop
fun main() {

    val visibility = Visibility()
    visibility.sayMyName()
    visibility.name = "Pablo"
    visibility.sayMyName()

    // visibility.sayMySurname() No se puede acceder porque es privado

    val visibilityTwo = VisibilityTwo()
    // visibilityTwo.sayAnotherThing() no puede acceder, porque es protected, solo se puede acceder en definición no en instancia
}

private open class Visibility {
    var name: String? = null
    private var surname: String? = null

    fun sayMyName() {
        name?.let { println("Mi nombre es $it") }
            ?: run { println("No tengo nombre") }
    }

    private fun sayMySurname() {
        println("Mi apellido es $surname")
    }

    protected fun sayAnotherThing() {
        println("Anything")
    }

}

private class VisibilityTwo : Visibility() { // si la clase es privada su hijos también tienen que serlo

    internal val age: Int = 26 // Internal sirve para exponer nuestra declaración fuera de nuestro módulo

    fun saySomething() {
        // Aunque herede no puede accerder a las partes privadas
        // println(super.surname)
        // println(super.sayMyName())

        sayAnotherThing() // Si se puede invocar desde la definición de la clase
    }
}

