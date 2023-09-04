fun main() {

    val pablo = PersonDataDestructured("Pablo", "Barrio", 26)
    // pablo.age

    val (name, surname) = pablo
}

// En las data classes por defecto vienen implementados los métodos
data class PersonDataDestructured(val name: String, val surname: String, private val age: Int) {



}