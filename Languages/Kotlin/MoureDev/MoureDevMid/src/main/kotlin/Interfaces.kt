//Las interfaces pueden contener definiciones de funciones abstractas y/o implementaciones pero nunca estado
fun main() {

    // val gamer = Game() No se puede intanciar
    // val gamer = Gamer("LoL")
    val gamer = Gamer()
    gamer.game
    gamer.play()
    gamer.stream()

}

// No pueden tener constructor ni propiedades privadas
interface Game {

    val game: String

    fun play() // por defacto es abstracta

    fun stream() {
        println("Stremeando el juego $game")
    }

}

//class Gamer2(override val game: String): Game
class Gamer : Game {

    // Tambien se pueden implementar variables así
    override val game: String
        get() = "LoL"

    override fun play() {
        println("Jugado al juego $game")
    }

}

