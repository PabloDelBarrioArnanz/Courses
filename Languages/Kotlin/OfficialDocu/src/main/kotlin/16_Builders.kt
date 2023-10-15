// Builders
// Type-safe builders allow creating Kotlin-based domain-specific languages (DSLs)
/*
    html {
            head {
                title {+"XML encoding with Kotlin"}
            }
            body {
                h1 {+"XML encoding with Kotlin"}
                p  {+"thi ...}
            }
         }
*/
data class Greeting(val default: String)
data class PersonData(val age: Int, val greeting: Greeting)

class PersonBuilder {
    var age = 0
    private val greetingBuilder = GreetingBuilder()

    fun greeting(init: GreetingBuilder.() -> Unit) {
        greetingBuilder.init()
    }

    fun build(): PersonData { return PersonData(age, greetingBuilder.build()) }
}

class GreetingBuilder {
    var greeting = ""
    fun build(): Greeting { return Greeting(greeting) }
}

fun buildPerson(init: PersonBuilder.() -> Unit): PersonData {
    val builder = PersonBuilder()
    builder.init()
    return builder.build()
}

val myCreatedPerson = buildPerson {
    age = 26 // Here it's like we are inside the person builder then we can access to his age property
    greeting {
        greeting = "Saludos!"
    }
}

val myCreatedPerson2 = PersonData(26, Greeting("Saludos!"))

// Scope control with @DslMarker
// That way we can control for example in the html example to don't allow to create a head inside another head
// TODO create a complete example


// Builder with builder type inference
fun addEntryToMap(baseMap: Map<String, Number>, additionalEntry: Pair<String, Int>?) {
    val myMap = buildMap {
        putAll(baseMap)
        if (additionalEntry != null) {
            put(additionalEntry.first, additionalEntry.second)
        }
    }
}

// Way to create own inference builders
fun <V> buildList(builder: MutableList<V>.() -> Unit) { null }