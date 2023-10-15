import kotlin.properties.Delegates
import kotlin.reflect.KProperty

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