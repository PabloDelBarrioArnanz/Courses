// Null Safety

// Nullable types and non-nullable types (some types cant handle a null value)

// var a: String = null error
var a: String? = null
// val length = a.length error
val length = a?.length

// Only if 'a' it's present let operation will be performed
val v = a?.let { println(it) }

// In this case only getManager will be executed if person and department and head are present
// person?.department?.head = managersPool.getManager()

// Nullable receiver
val person: Person? = null
// logger.debug(person.toString()) -> Logs "null", does not throw an exception

fun printMyValue(value: String?): Unit {
    value?.let { println(it) }
}

val exe1 = printMyValue("Hi!") // In Java if we have an argument Optional (null receiver) when not null must be encapsulated to be sent Optional.of("Hi!")
val exe2 = printMyValue(null)

// Elvis Operator don't exist in Kotlin but if/else it's an expression then:

// val l: Int = if (a != null) a.length else -1
val l: Int = a?.length ?: -1

// Also this expression can be used to perform other actions
fun foo(node: String?): String? {
    val parent = node?.length ?: return null
    val name = node?.length ?: throw IllegalArgumentException("name expected")
    // ...
    return ""
}

// The !! Operator transforms the nullable type into non-nullable
val nullable: String? = ""
val nonNullable: String  = nullable!!

// Safe cast
val toCast = ""
val aInt1: Int = toCast as Int // This casi will throw ClassCastException
val aInt2: Int? = toCast as? Int // This case will be just null