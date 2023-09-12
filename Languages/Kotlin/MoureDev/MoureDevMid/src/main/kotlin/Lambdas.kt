fun main() {

    val myInLint = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    myInLint.filter { myInt -> myInt > 5 } // mas similar a Java
    val filteredList = myInLint.filter { it > 5 }
    println(filteredList)

    val otherFilter1 = myInLint.filter { myInt ->
        if (myInt == 1)
            return@filter true
        myInt > 5
    }

    println(otherFilter1)

    // La misma función
    val mySum1 = fun(x: Int, y: Int): Int {
        return x + y
    }
    val mySum2 = fun(x: Int, y: Int): Int = x + y
    val mySum3: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    val mySum4 = { x: Int, y: Int -> x + y }

    // Lambda que extiende la clase int
    val intPlus: Int.(Int) -> Int = Int::plus // Referencia a método { it.plus(this) }
    println(intPlus.invoke(1, 1))
    println(intPlus(1, 2))
    println(2.intPlus(3))


    fun invokeLambda(function: (Double) -> Boolean): Boolean {
        return function(4.329)
    }

    val result1 = invokeLambda({ it % 2 == 0.0 })

    val result2 = invokeLambda { it % 2 == 0.0 } // Cuando una funcion recibe una lamda como parámetro se puede auto invocar así

    fun invokeLambdaWithMoreParameter(x: Int, y: Int, function: (Int, Int) -> Int): Int {
        return function(x, y)
    }

    invokeLambdaWithMoreParameter(5, 6, {x, y -> x + y})
    invokeLambdaWithMoreParameter(5, 6) {
            x, y -> x + y
    }

    // infix function solo se permite 1 parámetro y debe extender una clase
    infix fun Int.plus(b: Int): Int = this + b

    val sum = 34 plus 1

    println(sum)

}
