package playground.variance


interface Fruit

class Orange: Fruit, Comparable<Fruit> {

    override fun compareTo(other: Fruit): Int = 1
}

fun <T : Comparable<T>> sort(list: List<T>) {

}

fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
        where T : Fruit,
              T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}

fun main() {
    sort(listOf(Orange()))
    copyWhenGreater(listOf(Orange()), Orange())
}