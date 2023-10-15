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