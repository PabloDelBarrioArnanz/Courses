import java.util.*
import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator
import kotlin.math.abs
import kotlin.math.cos
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

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
    val text = when (result) { // if we use when as expression else is mandatory except if all case are cover
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

    // Functions Intro
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


    // Returns and jumps
    val s = Exception("Hi There!").message ?: return

    // labels
    myLoop@ for (i in 1..100) {
        myLoopOther@ for (j in 1..100) {
            if (j == 5) break@myLoop // we can select witch loop break or continue
        }
    }

    // labels also can be used to return at these point
    fun foo1() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return // non-local return directly to the caller of foo()
            print(it)
        }
        println("this point is unreachable")
    }

    fun foo2() {
        listOf(1, 2, 3, 4, 5).forEach lit@{
            if (it == 3) return@lit // local return to the caller of the lambda - the forEach loop also we can use implicit name return@foreach
            print(it)
        }
        print(" done with explicit label")
    }

    // return@a 1 this means return 1 at label @a


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
    val a: Int? = try {
        input.toInt()
    } catch (e: NumberFormatException) {
        null
    }

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
// method print toString(), compare equals or == , copy copy(), componentN()
// Data classes cannot be abstract, open, sealed, or inner
// All parameters must be val or var and at least 1
// Some times are better option than Pair or Triple
data class User(val name: String, val id: Int)

// Copy can change values
val user = User("pablo", 324)
val copiedValue = user.copy(name = "Pablo2")

// Compiler only use properties in the constuctor to build the equals and hashcode
// Then in this case 2 person with same name and different age are equal
data class Person(val name: String) {
    var age: Int = 0
}

// Works by order no by name
fun destructuring() {
    val (destructuredName, destructuredId) = User("pablo", 324)
    val (otherId) = User("pablo", 324) // otherId = pablo
    val (_, onlyNameNeed) = User("pablo", 324) // as works in other we need to avoid some parameters
}


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
    override var prop2: Int =
        5 // As in inheritance classes a var property can be overridden as var or val but not in the other way

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
typealias Predicate<T> = (T) -> Boolean
typealias MyTypeAlias = (number: Int) -> Int

val myTypeAliasMultiplier: MyTypeAlias = { it * 3 } // myTypeAliasMultiplier (Int) -> Int
val noTypeAliasMultiplier: (number: Int) -> Int = { it * 3 } // myTypeAliasMultiplier (Int) -> Int
val myValueTypeAlias = myTypeAliasMultiplier(4) // 12
val noValueTypeAlias = noTypeAliasMultiplier(4) // 12

// How to choose between SAMs and TypeAliases
// If your API needs to accept a function (with parameters and return value) -> typealias to give it a shorten name
// If your API needs to accept a more complex entity than a function -> separate it to an interface


// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Visibility
// Kotlin has 4 modifiers public, private, protected and internal
// By default is public, it's similar to java an internal it's visible everywhere in the same module
// protected or internal when are overridden has the same visibility if not specified


// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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

// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// Generic In/Out/Where
class Box<T>(t: T)

val box1: Box<Int> = Box<Int>(1)
val box2 = Box(1)

// Variance
// Kotlin has no wildcard, but why Java does?
// Java uses wildcards to increase flexibility
// Bcs java type are invariant, so List<String> is not a subtype from List<Object>
// This is to avoid this code compiles and broke in runtime

/*
    List<String> strs = new ArrayList<String>();
    List<Object> objs = strs; // !!! A compile-time error here saves us from a runtime exception later.

*/

// Se another example
// If java addAll method signature would be like this
//     void addAll(Collection<E> items)

// Then you wouldn't can do this bcs Collection<String> is not a subtype from Collection<Object>, Do not confuse with add an item, this is list type not item type
/*
      void copyAll(Collection<Object> to, Collection<String> from) {
            to.addAll(from);
      }
*/
// The actual signature of addAll is
/*
     interface Collection<E> ... {
        void addAll(Collection<? extends E> items); // The wildcard type argument ? extends E indicates that this method accepts a collection of objects of E or a subtype of E, not just E itself
     }
*/
// Collection<String> is a subtype of Collection<? extends Object>.
// In other words, the wildcard with an extends-bound (upper bound) makes the type covariant.

// Other mechanism super
// If we have a list of string and get make a get we can read it as an object
// In the same way with the list if we want to put an object in the list we need
// List<? super String> read as strings or any of its supertypes
// This is called contravariance

// Summary
// Ihe wildcard with an extends-bound (upper bound) makes the type covariant List<? extends E>
// Ihe wildcard with a super-bound (bottom bound) makes the type contravariance List<? super E>

// In kotlin there is a way to make this List<? extends E> in a simple way with no adding complex
// Declaration-site playground.variance
class Source<out T>(val t: T) {
    // <? extends T>
    fun nextT(): T { // producer
        return t
    }
}

val s1 = Source(1)
val s2: Source<Any> = s1
// The out modifier is called a playground.variance annotation it provides declaration-site playground.variance
// In contrast with Java's use-site playground.variance where wildcards in the type usages make the types covariant
// In other words, you can say that the class C is covariant in the parameter T, or that T is a covariant type parameter
// You can think of Source as being a producer of T's, and NOT a consumer of T's.


// Also exists in annotation, witch makes a type parameter contravariant (can only be consumed and never produced)
interface Comparable<in T> {
    operator fun compareTo(other: T): Int // consumer
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, you can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x // OK!
}

// Type projections Use-site playground.variance
fun copy(from: Array<out Any>, to: Array<Any>) { // Array<? extends String>
}

val ints: Array<Int> = arrayOf(1, 2, 3)
val any = Array<Any>(3) { "" }
val copyValue = copy(ints, any)

fun fill(dest: Array<in String>, value: String) { // Array<? super String>

}

val dest1: Array<String> = arrayOf("")
val dest2: Array<Any> = arrayOf()

val fillValue1 = fill(dest1, "")
val fillValue2 = fill(dest2, "")

// Star-projections
// Function<*, String> means Function<in Nothing, String>.
// Function<Int, *> means Function<Int, out Any?>.
// Function<*, *> means Function<in Nothing, out Any?>

// Also can be used _ for automatically infer a type of the argument when other types are explicitly specified

// Also generic function can be used as extensions
fun <T> T.basicToString(): String { // extension function
    return ""
}

val basicString = Source(1).basicToString()

// Upper bounds if not specified is Any?
fun <T : Comparable<T>> sort(list: List<T>) {} // Any type witch implements Comparable
//val fail = sort(listOf(HashMap<Int, String>())) // Error: HashMap<Int, String> is not a subtype of Comparable<HashMap<Int, String>>


// If you need to define more than one restriction you can use where
// T type must implement both CharSequence and Comparable
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
        where T : CharSequence,
              T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}

// Type erasure
val mutableList = mutableListOf("")
val result = if (mutableList is List<*>) {
    mutableList.forEach { println(it) } // The items are typed as `Any?`
} else null

fun handleStrings(list: MutableList<String>) {
    if (list is ArrayList) {
        // `list` is smart-cast to `ArrayList<String>`
    }
}

// uncheck cast
fun readDictionary(file: String): Map<String, *> = mutableMapOf("" to "")

// Warning: Unchecked cast: `Map<String, *>` to `Map<String, Int>`
val intsDictionary: Map<String, Int> = readDictionary("ints.dictionary") as Map<String, Int>

// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// Nested and inner (see MoureDev mid-tutorial)

// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


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

// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// Objects

// Object as expressions and declarations
// Creates objects of anonymous classes with object keyword
val helloWorld = object {
    val hello = "hello"

    override fun toString(): String = "This is an overridden toString method"
}

// Also an anonymous object can inherit from other classes
/*
    window.addMouseListener(object : MouseAdapter() { // the object it's implementing MouseAdapter
        override fun mouseClicked(e: MouseEvent) {}

        override fun mouseEntered(e: MouseEvent) {}
    })
*/

open class A(x: Int) {
    open val y: Int = x
}

interface B

val ab: A = object : A(1), B { // Anonymous extending A with properties and implementing B
    override val y = 15
}

// An object can be returned from a function
// If it's local or private but not inline declaration all members are accessible
class C {
    private fun getObject() = object { // Return type it's Any
        val x: String = "x"
    }

    fun printX() {
        println(getObject().x)
    }
}

interface ToImplement {
    fun toImplementMethod()
}

class NewC {
    // The return type is Any; x is not accessible
    fun getObject() = object {
        val x: String = "x"
    }

    // The return type is A; x is not accessible
    fun getObjectA() = object : ToImplement {
        override fun toImplementMethod() {}
        val x: String = "x"
    }

    // The return type is B or ToImplement we can choose; funFromA() and x are not accessible
    fun getObjectB(): B = object : ToImplement, B { // explicit return type is required
        override fun toImplementMethod() {}
        val x: String = "x"
    }
}

// Accessing to private and public
class Wrapper {
    private var privateCounter = 0
    var publicCounter = 0

    val myObject = object {
        fun increment() {
            privateCounter++
            publicCounter++
        }
    }
}

// Object as declarations
// The singleton pattern it's easy to declare with objects
// In this case in not an expression
object DataProvider {
    fun registerData() {}
}

val provider = DataProvider.registerData()

// Data objects
data object MyDataObject {
    val x = "text"
}

// Data objects has implemented the equals and toString method
// But it's better to compare it with == instance of === bcs can fail if there are more than one instance created in runtime
// This is only possible cheating for example reflection

// Data Objects has no copy neither componentN like data classes

// Companion Objects
class ClassWithCompanionNamed {
    companion object Factory { // Name can be omitted
        fun create(): ClassWithCompanionNamed = ClassWithCompanionNamed()
    }
}

val instance = ClassWithCompanionNamed.create()

class ClassWithCompanionNotNamed {
    companion object {
        fun create(): ClassWithCompanionNotNamed = ClassWithCompanionNotNamed()
    }
}

// With name or not the class name referred to companion object
val x = ClassWithCompanionNamed
val y = ClassWithCompanionNotNamed

// Companion objects can implement interfaces

interface Factory<T> {
    fun create(): T
}

class MyClassFactory {
    companion object : Factory<MyClassFactory> {
        override fun create(): MyClassFactory = MyClassFactory()
    }
}

val f: Factory<MyClassFactory> = MyClassFactory

// Object expressions are executed an initialized immediately
// Object declarations are initialized lazily (when accessed for the first time)
//      Companion object is initialized when the corresponding class is loaded


// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// Delegation
// This pattern it's a good alternative to implementation inheritance
// A class Derived can implement an interface Base by delegating all of its public members to a specified object

// Normal interface
interface Base {
    fun printMessage()
    fun printMessageLine()
}

// Normal class implementing interface
class BaseImpl(val x: Int) : Base {
    override fun printMessage() {
        print(x)
    }

    override fun printMessageLine() {
        println(x)
    }
}

// Class witch instance of inherit from BaseImpl derived it and can redefine or not any methods
class Derived(b: Base) : Base by b {
    override fun printMessage() {
        print("abc")
    }
}

fun tryIt() {
    val b = BaseImpl(10)
    Derived(b).printMessage()
    Derived(b).printMessageLine()
}

// Other example
interface ClosedShape {
    fun area(): Int
}

class RectangleShape(val width: Int, val height: Int) : ClosedShape {
    override fun area() = width * height
}

// When a window it's created any implementation of CloseShape can be passed as parameter
class Window(private val bounds: ClosedShape) : ClosedShape by bounds

// Delegated properties
// Lazy properties: the value is computed only on first access
// Observable properties: listeners are notified about changes to this property (observable pattern)
// Storing properties in a map instead of a separate field for each property

// Property delegator must have getValue and setValue operator methods
class CustomerDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

class ExampleDelegatedProperties {
    var delegated: String by CustomerDelegate()

    // Implements a lambda with 3 parameters, the property, oldValue, newValue
    var delegatedObservable: String by Delegates.observable("defaultValue") { prop, old, new ->
        println("$old -> $new")
    }
}

var topLevelInt: Int = 0

class ClassWithDelegate(val anotherClassInt: Int)

class ClassWithDelegation(var memberInt: Int, val anotherClassInstance: ClassWithDelegate) {
    var delegatedToMember: Int by this::memberInt
    var delegatedToTopLevel: Int by ::topLevelInt

    val delegatedToAnotherClass: Int by anotherClassInstance::anotherClassInt
}

// Extends a class with a new property delegated
var ClassWithDelegation.extDelegated: Int by ::topLevelInt

// Storing properties in a map
class UserDelegation(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

val userDelegation1 = UserDelegation(
    mapOf(
        "name" to "John Doe",
        "age" to 25
    )
)
val userDelegation2 = UserDelegation(
    mapOf(
        "userName" to "John Doe", // no matters key name bcs class delegates in some key with type string
        "userAge" to 25
    )
)

// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// TypeAliases (see MoureDev mid-tutorial)

// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


// Functions (see Functions Intro in this file)

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
// A.(B) -> C can be instantiated with a special form of function literals – function literals with receiver
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
// So, just to recap, when we’re passing a lambda to a function, the following happens under the hood:
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


// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

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
