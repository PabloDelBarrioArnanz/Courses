// Extensions
// Kotlin provides the ability to extend a class or an interface with new functionality
// Are resolved statically
// If extension it's defined as top level his scope is the package, but can be imported
// If extension it's defined for a class inside another class, only has effect in that class
// Extension can be overridden if are declared as open
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}

val list = mutableListOf(1, 2, 3).swap(0, 2)

// If a member and function has the same sign member wins
class Example {
    fun printFunctionType() {
        println("Class method")
    }
}

fun Example.printFunctionType() {
    println("Extension function")
}

val nothing = Example().printFunctionType() // "Class method"

// Nullable
fun Int?.myToString(): String {
    return this?.toString() ?: "No value found"
}

fun printNumber() {
    val num1: Int = 4
    // num2.myToString()
    var num2: Int? = 4
    num2.myToString()
}

// Also we can extend properties
val <T> List<T>.lastIndex: Int
    get() = size - 1

// val House.number = 1 error: initializers are not allowed for extension properties

// Companion alsa can be extended
class MyClass {
    companion object {

    }  // will be called "Companion"
}

fun MyClass.Companion.printCompanion() {
    println("companion")
}

fun main() {
    MyClass.printCompanion()
}