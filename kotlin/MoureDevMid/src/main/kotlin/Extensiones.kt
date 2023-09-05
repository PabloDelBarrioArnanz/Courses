import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Las extensiones permiten a�adir funcionalidad a clases que no son nuestras sin modificar el archivo base
// Con la extension dentro del m�dulo estar� disponible estaticamente la funcion
fun main() {
    println(LocalDate.now())
    println(LocalDate.now().customFormat())

    var myDate: LocalDate? = null
    if (myDate != null) {
        println(myDate.customFormat())
    }
    println(myDate.customFormatWithNull())
    println(LocalDate.now().customFormatWithNull())

    println(LocalDate.now().magicNumber)
    println(LocalDate.now().formatSize)
}

fun LocalDate.customFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("yy-MM-dd")
    return formatter.format(this) // this porque estar�a dentro de la clase LocalDate
}

// Se puede extender de la clase con validaci�n de nulo
fun LocalDate?.customFormatWithNull(): String {
    val formatter = DateTimeFormatter.ofPattern("yy-MM-dd")
    return this?.let { formatter.format(it) } ?: "No date available"
}

// Tambi�n se puede extender propiedades
val LocalDate.magicNumber: Int
    get() = 42

// Las extensiones on interoperables
val LocalDate.formatSize: Int
    get() = this.customFormat().length