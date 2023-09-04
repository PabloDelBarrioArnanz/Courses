import java.util.*

fun main() {
    test()
}

// Lecci�n 1 Enum Class
enum class Direction(val dir: Int) { // Propiedades
    NORTH(1),
    WEST(-1),
    EAST(1),
    SOUTH(-1);

    fun description(): String {
        // El when no compila si no est�n todos los valores en definidos, si no queremos poner alguno podemos usar else
        return when (this) {
            NORTH -> "La direcci�n es norte"
            WEST -> "La direcci�n es oeste"
            EAST -> "La direcci�n es este"
            else -> "Otra direcci�n"
        }
    }
}

private fun someDirection(): Direction {
    println("Valor en string del enum -> ${Direction.NORTH.name}")
    println("Propiedad del enum -> ${Direction.NORTH.dir}")
    println("Posici�n en el enum -> ${Direction.NORTH.ordinal}")
    println("Funci�n del enum -> ${Direction.NORTH.description()}")
    return Direction.NORTH
}

enum class Commander(private val selector: (String) -> Boolean, val executor: (String) -> String) {
    TO_UPPER_CASE({ selected -> selected === "TO_UPPER_CASE" }, { value -> value.uppercase(Locale.getDefault()) }),
    TRIM({ selected -> selected === "TRIM" }, { value -> value.trim() });

    object CommanderObj {
        fun checkOperation(selector: String, value: String): String {
            return entries.filter { commanderCase -> commanderCase.selector.invoke(selector) }
                .map { commanderCase -> commanderCase.executor.invoke(value) }
                .firstOrNull()
                ?: ""
            // .first() throws exception si no encuentra ninguno
            // .single() throws exception si no encuentra ninguno

            /*
                return entries.find { commanderCase -> commanderCase.selector.invoke(selector) }// Cierra el flujo direcctamente
                    ?.executor?.invoke(value) ?: value // null chaining and orElse de java

             */
        }
    }
}

private fun test() {
    println(Commander.TO_UPPER_CASE.executor.invoke("MyVal"))
    println(Commander.CommanderObj.checkOperation("TRIM", "My Val"))
    println(Commander.CommanderObj.checkOperation("Other operation", "My Val"))
}

