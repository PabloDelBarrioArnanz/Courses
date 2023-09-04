fun main(args: Array<String>) {
    println("Hello World!")

    // 1. Variables
    var myString = "Esto es una cadena de texto"
    myString = "var se puede reasignar"
    // myString = 2 no se puede myString es texto

    var otherString: String = "Sobre tipado no necesario porque lo infiere"

    var otherValue = 1
    // otherValue = 2 // val no se puede reasignar
    otherValue.minus(1) // No reasigna a other value

    val doubleValue = 1.2 // Double

    // Floats, boolean...

    val myConstant = 1
    // otherValue = 2 // val no se puede reasignar

    // 2. Control de Flujo

    if (myConstant == 1) {
        println("El valor es 1")
    } else if (myConstant == 2 && true || false) {
        println("El valor es 2")
    } else {
        println("El valor no es 1")
    }

    // Estructuras de datos
    val myImmutableList = listOf("Pablo", "26", "SE") // Inmutable
    val myMutableList = mutableListOf("Pablo", "26", "SE") // Mutable aunque sea val, lo que no se puede es reasignar
    println(myImmutableList)
    myMutableList.add("otherString")

    // setOf, mapOf...

    // bucles
    for (myPart in myMutableList);

    var myCounter = 0
    while (myCounter <= myMutableList.count()) {
        myCounter++
    }

    // Optional
    var myOptional: String? = null

    fun myFunction(paramter1: String): Int {
        return "Hola $paramter1".length
    }

    // Clases
    class MyClass(val name: String, val age: Int)
    MyClass("Pablo", 26)

}
