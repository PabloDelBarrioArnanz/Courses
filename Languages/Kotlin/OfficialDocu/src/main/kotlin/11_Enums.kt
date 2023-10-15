import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

// Enum classes
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

enum class Greet {
    HELLO {
        override fun greetings(name: String) = "Hello $name"
    },
    BYE {
        override fun greetings(name: String) = "BYE $name"
    };

    abstract fun greetings(name: String): String
}

val hello = Greet.HELLO.greetings("Pablo")

// An enum also can implement interfaces
// In this case it's implementing 2 interface, BinaryOperator apply function and IntBinaryOperator applyAsInt function
enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        override fun apply(t: Int, u: Int): Int = t * u
    };

    // As we need to implement both interfaces we can say applyAsInt will be implemented as apply now both interfaces uses apply
    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}

val plus2 = IntArithmetics.PLUS.apply(1, 2)

// EnumClass.valueOf(value: String): EnumClass
// EnumClass.values(): Array<EnumClass>

fun printEnum() {
    println(Direction.NORTH.name) // prints NORTH
    println(Direction.NORTH.ordinal) // prints 0
}

// Inline value classes
// Are simple wrappers
// Can have init and secondary constructor and are allowed to inherit and implement, but can't have backing fields and lateinit properties
@JvmInline
value class Password(private val s: String)

// No actual instantiation of class 'Password' happens
// At runtime 'securePassword' contains just 'String'
val securePassword = Password("Don't try this in production")

// Can be similar to typealias but with no wrapping
