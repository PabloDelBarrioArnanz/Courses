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