import java.util.*

fun main(args: Array<String>) {
    println("Official Docu research")

    // Variables
    /*
       Variables type are inferred but also can be declared
       Basic types
        - Integers (Byte, Short, Int, Long)
        - Unsigned integers (UByte, UShort, UInt, ULong)
        - Floating-point numbers (Float, Double)
        - Booleans (Boolean)
        - Characters (Char)
        - String (String)
    */
    val readOnly = "1" // read only value
    var modifiable: String = ""
    modifiable = "Other value"
    var unInicialized: String // Only var can be unInitialized but needs the type
    unInicialized = "test"

    // Type checks and cast
    val myVal: Any = ""
    myVal is String
    myVal !is String

    //Smart cast
    if (myVal is String) {
        // here I can use myVal as string
        myVal.length
    }
    if (myVal is String && myVal.length == 0) return
    if (myVal !is String || myVal.length == 0) return

    // String templates
    val name = "Pablo"
    val myTemplate = "Hello $name"
    val myTemplateWithExpression = "Hello ${name.uppercase(Locale.getDefault())}"

    // Collections
    /*
        List ordered collections of items
        Sets unique unordered collections of items
        Maps set of key-values pairs where keys are unique and map to only one value
    */
    // List has to string defined
    val readOnlyList = listOf("item1", "item2")
    val modifiableList = mutableListOf("item1", "item2")


    val sealedList: List<String> = modifiableList // casting to create a readOnly list from a modifiable list

    modifiableList.add("item3")
    modifiableList.remove("item3")
    readOnlyList.contains("Some element")
    "Some element" in readOnlyList

    // Set
    val readOnlyFruit = setOf("apple", "banana", "cherry", "cherry") // "apple", "banana", "cherry"

    // Map
    val readOnlyJuiceMenu = mapOf("apple" to 100, "kiwi" to 190, "orange" to 100)
    readOnlyJuiceMenu.keys
    readOnlyJuiceMenu.values

    // if/else
    val check = true
    if (check) {
        "True"
    } else {
        "False"
    }
    // In kotlin there is no ternary operator, but we can use if/else as expression with no {}
    val result = if (check) "True" else "False"

    // When
    when (result) {
        "1" -> println("One")
        "2" -> println("Two")
        else -> println("Unknown")
    }
    val text = when (result) { // if we use when as expression else is mandatory
        "1" -> "One"
        "2" -> "Two"
        else -> println("Unknown")
    }
    when {
        result.length < 10 -> println("Less than 10")
        result.length >= 10 -> println("More than 10")
    }

    // Ranges
    val intRange1: IntRange = 1..4 // 1,2,3,4
    val intRange2 = 1..<4 // 1,2,3
    val intRange3 = 4 downTo 1 // 4,3,2,1
    val intRange4 = 4 downTo 1 step 2 // 4,2
    val intRange5 = 'a'..'f' step 2 // a,c,e,f

    // Loops
    // For
    for (number in 1..5) {
    }

    val cakes = listOf("carrot", "cheese", "chocolate")
    for (cake in cakes) {
    }

    // Also exists while and doWhile

    // Functions
    fun hello1() { // No return type -> Unit
        return println("Hello!") // Same as println("Hello!")
    }

    fun hello2(): Unit {
        println("Hello!") // Same as println("Hello!")
    }

    fun hello(name: String): String {
        return "Hello $name"
    }

    fun log(message: String, prefix: String = "Info") { // Default value also can be a function call
        println("[$prefix] $message")
    }

    log(prefix = "Log", message = "Hello") // Named parameters bcs order altered
    log(message = "Hello") // Named parameter not necessary bcs the required parameter it's the first one
    // If the required parameter it's the second one, named parameter it's required for call with 1 parameter

    // Single expression function
    // A function with only one line can replace {} by =
    fun sumSingleExpression(x: Int, y: Int) = x + y

    // Lambda expressions
    val lambdaFunction1 = { value: String -> value.uppercase(Locale.getDefault()) }
    // If we define the type in the variable we can use it bcs it type it's defined
    val lambdaFunction2: (String) -> String = { it.uppercase(Locale.getDefault()) }
    val lambdaFunction3 = { String::plus }
    // If function has no parameters then () -> it's not necessary
    val lambdaFunction4 = { -> println("Some message") }
    val lambdaFunction5 = { println("Some message") }

    // A function assigned to a variable can be invoked like this
    lambdaFunction1("Value")
    lambdaFunction1.invoke("Value")

    // Pass it to another function
    // For example 'let' is a HOF then his parameter it's another function
    // HOF are extension form concrete types
    val myValue = "myValue"
    println(myValue.let { value -> lambdaFunction1(value) })
    println(myValue.let { lambdaFunction1(it) })

    val functionChain: (String) -> String = myValue.let { lambdaFunction1 } // This is not an invocation
    println(myValue.let { lambdaFunction5() }) // This is an invocation but how let must return something this will print Unit

    // BiFunction
    val myFunc1: (String, Int) -> String = { myText, number -> myText.plus(number) }
    myFunc1("text", 12)


    val lambdaFunction6: (String) -> String = { it.plus("") }
    // If we want to invoke a function inside a HOF () are not necessary
    myValue.let(lambdaFunction6)


    val myFunc2: (String, (String) -> String) -> String = { value, function -> function(value) }
    println(myFunc2("Hola", { s -> s.plus(", soy Pablo") }))
    // When we invoke a function with a lambda as last parameter we can define it in this way this is we use map { } or let { } in this way
    println(myFunc2("Hola") {
        it.plus(", soy Pablo")
    })

    myValue.let({ lambdaFunction5() }) // let without use {} directly

    val supplier: () -> String = { "Some value" }

    /*
        let { } defining a function inside the let
        let()   invoking function inside the let, that function can't have parameters bcs will be an execution let(function)
        let({}) defining a function not necessary let {}

    */
    val let1: (String) -> String = myValue.let { lambdaFunction6 }
    val let2: (String) -> String = myValue.let({ lambdaFunction6 })
    val let3: String = myValue.let({ lambdaFunction6("") })
    val let4: String = myValue.let { "" }


    fun toSeconds(time: String): (Int) -> Int = when (time) {
        "hour" -> { value -> value * 60 * 60 }
        "minute" -> { value -> value * 60 }
        "second" -> { value -> value }
        else -> { value -> value }
    }

    val timesInMinutes = listOf(2, 10, 15, 1)
    val min2sec = toSeconds("minute") // { value -> value * 60 }
    // println(timesInMinutes.map { value -> value * 60 }.sum())
    // println(timesInMinutes.map{ min2sec(it) }.sum())
    println(timesInMinutes.map(min2sec).sum())

    println({ string: String -> string.uppercase() }("hello")) // Invoke separately


    //todo returns and jumps

    // Exceptions
    /*
        Kotlin has no checked exceptions -> checked exceptions are an error
            Effective Java, 3rd Edition, Item 77: Don't ignore exceptions.
            Java's checked exceptions were a mistake (Rod Waldhoff)
    */
    try {
        throw Exception("Hi There!")
    } catch (e: Exception) {
        println("An exception was thrown " + e.message)
    } finally {
        // optional finally block always executed
    }

    // In kotlin try/catch block in an expression
    val input = "6578"
    val a: Int? = try { input.toInt() } catch (e: NumberFormatException) { null }

}

// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// Classes
// If class hasn't properties () are not necessary
// If class hasn't body {} are not necessary
// Parameters in first line are the first constructor, constructor keyword can be omitted if not annotations or modifiers
class PrivateContact private constructor(
    val id: Int = -1,
    val email: String
) {
    var myField1: String = ""
        get() = field
        set(value) {
            field = value
        }

    val myField2 get() = ""

    var myField3: Any? = null
    // @Inject set
}

class Contact(// properties with no val o var aren't accessible after instance created
    val id: Int = -1,
    val email: String
) {
    var category: String = ""

    constructor(id: Int) : this(id, "none") { // Secondary constructor
        this.category = "auto"
    }

    init {
        category = "Some category" // init blocks are executed after constructor, can be more than one or 0
    }

    // Member functions
    fun printId() {
        println("My id is $id")
    }
}

// Instantiating a class
val myContact = Contact(22435, "pablo@gmail.com")
// myContact.id
// myContact.email
// myContact.category
// myContact.printId()

// Data Classes
// Are particularly useful for storing data
// This kind of classes comes with other useful member functions
// method print toString(), compare equals or == , copy copy()...
data class User(val name: String, val id: Int)

// Copy can change values
val user = User("pablo", 324)
val copiedValue = user.copy(name = "Pablo2")


// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// Inheritance
open class OpenBase(p: Int) { // Only open class or functions can be used for inheritance
    open val test1: String = "father"
    open var test2: String = "father"

    open fun printSomething() {
        println("Hola")
    }
}

class Example1(p: Int, x: String) : OpenBase(p) {} // Send super parameters directly
class Example2 : OpenBase {
    override var test1: String = "child" // Override a property changing it from val to var but can be done reversed
    override var test2: String = "child"

    constructor(p: Int, x: String) : super(p) {
        // can do something here
    }

    override fun printSomething() { // If we use final override a child couldn't override it
        // super.printSomething() super can be called
        println("Hola")
    }
}

// Inheritance order OpenBase constructor > OpenBase init > Child constructor > Child init

open class Rectangle {
    open fun draw() { /* ... */
    }
}

interface Polygon {
    fun draw() { /* ... */
    } // interface members are 'open' by default
}

class Square() : Rectangle(), Polygon { // extends Rectangle and implements Polygon
    // The compiler requires draw() to be overridden:
    override fun draw() {
        super<Rectangle>.draw() // call to Rectangle.draw()
        super<Polygon>.draw() // call to Polygon.draw()
    }
}

class FilledRectangle : Rectangle() {
    override fun draw() {
        val filler = Filler()
        filler.drawAndFill()
    }

    inner class Filler {
        fun fill() {
            println("Filling")
        }

        fun drawAndFill() {
            draw() // // Calls FilledRectangle's implementation of draw()
            super@FilledRectangle.draw() // Calls Rectangle's implementation of draw()
            fill()
        }
    }
}

// Compile-time constants
// It must be a top level property or an object member
// Must be initialized with primitive types or String
// Can't have customer getter
const val CONST_PROPERTY: String = ""

// lateinit only applies for var
class MyTest {
    lateinit var subject: String // we can use isInitialized to check if it's already initialized

    fun setup() {
        subject = ""
    }
}

// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Interfaces

interface MyInterface {
    val prop1: Int
    var prop2: Int

    fun function1()
    fun function2() {
        println("From interface")
    }
}


interface MyInterfaceChild : MyInterface {
    fun iAmTheChild()
}

class Implementer : MyInterfaceChild {
    override val prop1: Int = 5
    override var prop2: Int = 5 // As in inheritance classes a var property can be overridden as var or val but not in the other way

    override fun function1() { // this function is required to be implemented
        println("Required")
    }

    override fun function2() {
        super.function2()
        println("No required")
    }

    override fun iAmTheChild() {
        println("Required")
    }
}

// If we have conflict in overriding name functions we can use super<ClassName>.method()

// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
typealias MyTypeAlias = (number: Int) -> Int
val myTypeAliasMultiplier: MyTypeAlias = { it * 3 } // myTypeAliasMultiplier (Int) -> Int
val noTypeAliasMultiplier: (number: Int) -> Int = { it * 3 } // myTypeAliasMultiplier (Int) -> Int
val myValueTypeAlias = myTypeAliasMultiplier(4) // 12
val noValueTypeAlias = noTypeAliasMultiplier(4) // 12

// How to choose between SAMs and TypeAliases
// If your API needs to accept a function (with parameters and return value) -> typealias to give it a shorten name
// If your API needs to accept a more complex entity than a function -> separate it to an interface