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


    // Classes


}
