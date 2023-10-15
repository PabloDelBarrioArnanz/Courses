// Sealed Classes and Interfaces
// A sealed class is abstract and his constructor are protected (by default) or private
// Represents restricted class hierarchy. All subclasses of a sealed class are known in compile time
// Third-party clients can't extend a sealed class
// In some sense, sealed classes are similar to enum
//  The set  of values in an enum type is also restricted
//  But each enum constant exists only as a single instance.
//  A subclass of a sealed class can have multiple instances
// Enum can implement a sealed interface but not extend a sealed class

sealed interface Error // has implementations only in same package and module
sealed class IOError : Error // extended only in same package and module
open class CustomError : Error // can be extended wherever it's visible

fun log(e: Error) = when (e) {
    is IOError -> {
        println("Error while reading file")
    }

    is CustomError -> {
        println("Custom error")
    }
    // the `else` clause is not required because all the cases are covered
}