

// This expression

// To denote the current receiver, you use 'this' expressions:
// In a member of a class, this refers to the current object of that class
// In an extension function or a function literal with receiver this denotes the receiver parameter that is passed on the left-hand side of a dot

// Also Kotlin has qualified this

class AA { // implicit label @AA
    inner class B { // implicit label @B
        fun Int.foo() { // implicit label @foo
            val a = this@AA // AA's this
            val b = this@B // B's this
            val c = this // foo()'s receiver, Int
            val c1 = this@foo // foo()'s receiver
        }
    }
}

// Implicit this
fun main() {

    fun printLine() { println("Top-level function") }

    class ImplicitClassContainer {
        private fun printLine() { println("Member function") }

        fun invokePrintLine(omitThis: Boolean = false)  {
            if (omitThis) printLine()
            else this.printLine()
        }
    }

    println(ImplicitClassContainer().invokePrintLine()) // Member function
    println(ImplicitClassContainer().invokePrintLine(omitThis = true)) // Top-level function
}

