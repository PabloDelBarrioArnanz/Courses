import kotlin.math.abs
import kotlin.math.cos

// Functions (see Functions Intro in 01_IntroOverview)

fun funSum(num1: Int, num2: Int = num1): Int { // Default parameters
    return num1 + num2
}

val resultFunSum = funSum(1, 2)
val resultFunSumDefault = funSum(1)
val namedFunSumDefault =
    funSum(num1 = 1) // Sometimes named parameters are need to indicate witch parameter we are sending

// If a HOI has the function parameter in the last position can be called with { }
fun foo(
    bar: Int = 0,
    baz: Int = 1,
    qux: () -> Unit,
) {
}

val r1 = foo(1) { println("hello") }     // Uses the default value baz = 1
val r2 = foo(qux = { println("hello") }) // Uses both default values bar = 0 and baz = 1
val r3 = foo { println("hello") }        // Uses both default values bar = 0 and baz = 1


// Single-expression functions
fun singleExpressionDouble(x: Int) = x * 2

// Infix notation
// They must be member functions or extension functions
// They must have a single parameter
// The parameter must not accept variable number of arguments and must have no default value

infix fun Int.infixFunction(x: Int): Int = x + this

val infixResult = 1 infixFunction 2

// Be careful has lower precedence than arithmetic operators
// 1 infixResult 2 + 3 is equivalent to 1 infixResult (2 + 3)

// But has higher precedence than boolean operators
// a && b xor c is equivalent to a && (b xor c)
//a xor b in c is equivalent to (a xor b) in c

class MyStringCollection {
    infix fun add(s: String) { /*...*/
    }

    fun build() {
        this add "abc"   // Correct
        add("abc")       // Correct
        //add "abc"        // Incorrect: the receiver must be specified
    }
}

// Kotlin supports local functions
fun parentFunction(number: Int) {
    fun childFunction() {

    }
    childFunction()
}

// Tail Recursive functions
val eps = 1E-10

// tailrec the compiler optimizes out the recursion
tailrec fun findFixPoint(x: Double = 1.0): Double =
    if (abs(x - cos(x)) < eps) x else findFixPoint(cos(x))


// Lambdas
// Fast example pretty similar to java
fun <T, R> Collection<T>.fold(initial: R, combine: (acc: R, next: T) -> R): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

val myAcc = listOf("1", "2", "3").fold(0) { acc, next ->
    next.toInt() + acc
}

// Callable reference
val getListSizeV1: (List<String>) -> Boolean = { list -> list.isEmpty() }
val getListSizeV2: (List<String>) -> Boolean = { it.isEmpty() }
val getListSizeV3: (List<String>) -> Boolean = List<String>::isEmpty

// Return value from lambda
val filter1 = listOf(1, 2, 3).filter {
    val shouldPass = it > 0
    shouldPass
}

val filter2 = listOf(1, 2, 3).filter {
    val shouldPass = it > 0
    return@filter shouldPass
}

// Destructuring lambda and ignore
val map = mapOf(1 to "1", 2 to "2").forEach { (_, value) -> println(value) }

// Closure
fun sum(ints: List<Int>) {
    var sum = 0
    ints.filter { it > 0 }
        .forEach {
            sum += it // In java cannot access to this val bcs must be final or effective final
        }
    print(sum)
}

// Function literal with receiver
// A.(B) -> C can be instantiated with a special form of function literals  function literals with receiver
// Inside the body of the function literal, the receiver object passed to a call becomes an implicit 'this
val sum = 1
val sum1: Int.(Int) -> Int = { other -> plus(other) }
val sum2: Int.(Int) -> Int = fun Int.(other: Int): Int = this + other

class HTML {
    fun body() {}
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}

val myHtml = html {
    body() // we can call body directly bcs we are like inside the receiver (HTML)
}

// This is used for type-safe builders


// Inline functions
// So, just to recap, when were passing a lambda to a function, the following happens under the hood:
//   At least one instance of a special type is created and stored in the heap
//   An extra method call will always happen
// The extra memory allocations get even worse when a lambda captures a variable: The JVM creates a function type instance on every invocation

// When using inline functions, the compiler inlines the function body. That is, it substitutes the body directly into places where the function gets called
// When using inline functions, there is no extra object allocation and no extra virtual method calls.


// Operator overloading
// Kotlin allows you to provide custom implementations for the predefined set of operators on types like (like + or *)

// +a  <=> a.unaryPlus()
// -a  <=> a.unaryMinus()
// !a  <=> a.not()
// a++ <=> a.inc()
// a-- <=> a.dec()
// much more

data class Point(val x: Int, val y: Int)

operator fun Point.unaryMinus() = Point(-x, -y)

val point = Point(10, 20)
val minusPoint = -point
