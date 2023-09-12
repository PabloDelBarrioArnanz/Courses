// Properties with var o val are public by default (can be made private) if there is no val or var its private
class User(var name: String, val lastName: String, age: Int = 0) {
    var fullName: String = ""

    // Getters and Setter can be overridden that way
    var id: String = ""
        get() {
            println("Getting property")
            return field
        }
        set(value) {
            println("Setting property")
            field = value
        }

    // only var
    // Allows us to have uninitialized variables and initialize it the
    lateinit var favMovie: String
    // var hateMovie: String error, can work if we set a init value or if its initialized in the init {}

    // only val
    // lazy initialization will be executed by first time only if it's call, next time will return the vale
    val greeting: String by lazy {
        println("Executing by first time lazy vale")
        "Hello my name is $name"
    }

    // It's executed after constructor
    init {
        fullName = "$name $lastName $age"
        id = "$name-$lastName-$age"
    }

    //
    constructor(name: String) : this(name, "NA", 99)
}

class Calculator {
    fun sum(a: Int, b: Int) = a + b

    companion object Calculator { // not necessary to be named, can implements interfaces
        fun sum(a: Int, b: Int) = a + b
    }
}

class SingletonClass {
    private constructor() {}

    companion object {
        private var instance: SingletonClass? = null

        val instanceManager: SingletonClass
            get() {
                if (instance == null) {
                    println("Building singleton")
                    instance = SingletonClass()
                }
                return instance!!
            }
    }

    fun sayHello() = println("Hello!")
}

object SingletonObject {
    init {
        println("Building singleton")
    }
    fun sayHello() = println("Hello!")
}

fun main() {
    val user = User("Pablo", "Barrio", 26)
    println(user.fullName)
    user.name = "Sr Pablo"
    println(user.fullName) // Pablo Barrio

    val userSecondary = User("Pablo")
    println(userSecondary.fullName)

    userSecondary.apply { name = "Sr Pablo" }
    println(userSecondary.name)
    println(userSecondary.id)
    userSecondary.id = "NotFound"
    println(userSecondary.id)

    user.favMovie = "some movie"

    println(userSecondary.greeting)
    println(userSecondary.greeting)
    println(userSecondary.greeting)

    val calculator = Calculator()
    val sum = calculator.sum(1, 1)
    val sumObject = Calculator.sum(1, 1)
    val companion = Calculator

    val singletonClass = SingletonClass.Companion
    singletonClass.instanceManager.sayHello()
    singletonClass.instanceManager.sayHello()

    SingletonObject.sayHello()
    SingletonObject.sayHello()
}