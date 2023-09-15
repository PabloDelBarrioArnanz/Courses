package playground.variance

interface KotlinFruit

class KotlinBanana : KotlinFruit

class KotlinCherry : KotlinFruit

fun addBanana(fruits: Array<KotlinFruit>) { // invariant
    fruits[0] = KotlinBanana()
}

fun addBananaToBananaOrBananaFather(fruits: Array<in KotlinBanana>) { // contravariant, Be careful allow any has it's a father from KotlinBanana!
    fruits[0] = KotlinBanana() // Consumer
}

fun addBananaToAnyFruitOrFruitArrayChild(fruits: Array<out KotlinFruit>) { // covariance it's used as producer, not consumer, cant make that
    // fruits[0] = KotlinBanana()
}



fun salad() {
    addBanana(arrayOf<KotlinFruit>(KotlinBanana()))
    //addBanana(arrayOf<KotlinBanana?>(KotlinBanana())) this both lines fails in compiling time
    addBanana(arrayOf<KotlinFruit>(KotlinCherry()))
    //addBanana(arrayOf<KotlinCherry>(KotlinCherry()))

    addBananaToBananaOrBananaFather(arrayOf<KotlinFruit>(KotlinBanana()))
    addBananaToBananaOrBananaFather(arrayOf<KotlinBanana>(KotlinBanana()))
    addBananaToBananaOrBananaFather(arrayOf<KotlinFruit>(KotlinCherry()))
    //addBananaToBananaOrBananaFather(arrayOf<KotlinCherry>(KotlinCherry())) Cherry is not a banana neither a banana child

    addBananaToAnyFruitOrFruitArrayChild(arrayOf<KotlinFruit>(KotlinBanana()))
    addBananaToAnyFruitOrFruitArrayChild(arrayOf<KotlinBanana>(KotlinBanana()))
    addBananaToAnyFruitOrFruitArrayChild(arrayOf<KotlinFruit>(KotlinCherry()))
    addBananaToAnyFruitOrFruitArrayChild(arrayOf<KotlinCherry>(KotlinCherry()))

}
