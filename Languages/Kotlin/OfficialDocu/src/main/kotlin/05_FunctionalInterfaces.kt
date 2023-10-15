// Functional Interfaces SAM Single Abstract Method interface

fun interface MySAM {
    fun accept(number: Int): Int
}

class ExampleClass1 {
    val mySamMultplier: MySAM = MySAM { it * 3 }

    val myValue = mySamMultplier.accept(4) // 12
}

class ExampleClass2 {
    val myValue = object : MySAM {
        override fun accept(number: Int): Int {
            return 3 * number
        }
    }
}

// TypeAlias
// Type aliases are names for existing types they don't create new types and SAM yes
// SAM can have other members like non-abstract methods
// SAM are syntactically and at runtime costly bcs need conversions
typealias Predicate<T> = (T) -> Boolean
typealias MyTypeAlias = (number: Int) -> Int

val myTypeAliasMultiplier: MyTypeAlias = { it * 3 } // myTypeAliasMultiplier (Int) -> Int
val noTypeAliasMultiplier: (number: Int) -> Int = { it * 3 } // myTypeAliasMultiplier (Int) -> Int
val myValueTypeAlias = myTypeAliasMultiplier(4) // 12
val noValueTypeAlias = noTypeAliasMultiplier(4) // 12

// How to choose between SAMs and TypeAliases
// If your API needs to accept a function (with parameters and return value) -> typealias to give it a shorten name
// If your API needs to accept a more complex entity than a function -> separate it to an interface