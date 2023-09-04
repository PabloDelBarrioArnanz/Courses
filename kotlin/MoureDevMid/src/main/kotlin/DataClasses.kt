fun main() {

    val pablo1 = Worker("Pablo", 26, "Software Engineer")
    pablo1.lastWork = "Lifeguard"
    val (name1, age1) = pablo1

    val pablo2 = WorkerClass("Pablo", 26, "Software Engineer")
    pablo2.lastWork = "Lifeguard"

    //val (name2, age2) = pablo2 error no existe el m�todo component1() ni component2()

}

// Obligatorio necesita al menos 1 propiedad
data class Worker(val name: String, val age: Int, val work: String = "Desempleado") {

    var lastWork: String = ""

    // Ya tiene implementado los m�todos equals and hashcode
    // El m�todo toString
    // M�todo Copy
    // M�todos components compoenentN

}

class WorkerClass(val name: String, val age: Int = 1, val work: String) {

    var lastWork: String = ""

}