interface Logger {
    fun info(message: String)
}

val loggerContext = object : Logger {
    override fun info(message: String) {
        println("[INFO] $message")
    }
}

interface Printable<A> {
    fun A.toPrint(): String
}

val printablePerson = object : Printable<Person> {
    override fun Person.toPrint(): String = "Hola soy $name $surname"
}

data class Person(val name: String, val surname: String)

val PERSONS = listOf(Person("Pablo", "Barrio"))

interface Persons {
    fun findPersonByName(name: String): Person?
}

context (Logger)
class PersonService : Persons {
    override fun findPersonByName(name: String): Person? {
        info("Searching person")
        return PERSONS.find { it.name == name }
    }
}

context (Logger, Printable<Person>)
class PersonController(private val personService: PersonService) {
    fun findPersonByName(name: String): String? {
        info("Starting to search person")
        return personService.findPersonByName(name).let { it?.toPrint() }
    }
}

fun main() {
    with(loggerContext) {
        with(printablePerson) {
            val personService = PersonService()
            val personController = PersonController(personService)
            val findPersonByName = personController.findPersonByName("Pablo")
            println(findPersonByName)
        }
    }
}