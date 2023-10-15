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