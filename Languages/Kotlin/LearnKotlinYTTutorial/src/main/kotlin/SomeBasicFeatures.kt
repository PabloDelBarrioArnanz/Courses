// Default parameters
fun sendMessage(name: String = "Anonymous", message: String) {
    println("Name = $name and message = $message")
}

fun sendMessageOneParam(name: String = "Anonymous") {
    println("Name = $name")
}

fun getDefaultName() = "Anonymous"
const val defaultMessage = "Hello"

fun sendMessageSpecialDefault(name: String = getDefaultName(), message: String = defaultMessage) {
    println("Name = $name")
}

// VarArgs
fun sum(vararg values: Int) = values.sum()

//When statement
fun getTime(number: Int): String {
    return when (number) {
        1,2,3 -> "Too early"
        4,5 -> "Also early"
        in 6 .. 10 -> "Almost"
        !in 13 .. 24 -> "Late!"
        !in 26 .. 30 -> "More Late!"
        is Int -> "Has no sense always int"
        else -> "I don't know"
    }
}

fun main(args: Array<String>) {
    sendMessage("Pablo", "Hola")
    sendMessage(message = "Hola") // as is the second parameter the required one, we have to name it
    sendMessageOneParam()
    sendMessageOneParam("Pablo")
    sendMessageOneParam(name = "Pablo")

    println(sum(1, 2, 3))
    println(sum(1, 2, 3, 4, 5))

    println(getTime(12))

}