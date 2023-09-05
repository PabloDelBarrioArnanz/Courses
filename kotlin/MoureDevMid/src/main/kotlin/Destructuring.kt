fun main() {

    val pablo = PersonDataDestructured("Pablo", "del", "Barrio", { "Hola! Soy Pablo" }, 26)
    // pablo.age

    val (name, surname, sayHello) = pablo // para las propiedades privadas no funciona
    // surname es midValue porque es por orden no por nombre, no se puede llegar a la 3 sin desestructurar o ignorar la anterior
    println("My name and surname destrucutred are $name and $surname")
    val (name2, _, surname2) = pablo // para las propiedades privadas no funciona
    println("My name and surname destrucutred are $name2 and $surname2") // surname es midValue porque es por orden
    pablo.component2()

    // tambien se puede aplicar a mapas
    val map = mapOf(1 to "Pablo", 2 to "Moure")
    for (element in map) {
        println("${element.component1()} -> ${element.component2()}")
    }
    for ((id, name) in map) {
        println("$id -> $name")
    }

    val personList: List<PersonDataDestructured> = listOf(pablo)
    personList.forEach { (name, surname) -> println("My name and surname destrucutred are $name and $surname") }
}

// En las data classes por defecto vienen implementados los métodos
data class PersonDataDestructured(
    val name: String,
    val midValue: String,
    val surname: String,
    val sayHello: () -> String,
    private val age: Int
)
