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

// Compiler only use properties in the constructor to build the equals and hashcode
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

// { a -> ... } // one parameter
// { a, b -> ... } // two parameters
// { (a, b) -> ... } // a destructured pair
// { (a, b), c -> ... } // a destructured pair and another parameter