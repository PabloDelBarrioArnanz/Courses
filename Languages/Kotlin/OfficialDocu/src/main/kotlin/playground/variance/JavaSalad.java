package playground.variance;

import java.util.ArrayList;
import java.util.List;

interface JavaFruit {
}

class JavaBanana implements JavaFruit {
}

class JavaCherry implements JavaFruit {
}

class JavaSalad {

    public static void addBanana(JavaFruit[] javaFruits) {
        javaFruits[0] = new JavaBanana();
    }

    public static void addBananaToFruitsList(List<JavaFruit> javaFruits) {
        javaFruits.add(new JavaBanana());
    }

    public static void addBananaToBananaOrFatherList(List<? super JavaBanana> javaFruits) {
        javaFruits.add(new JavaBanana());
    }

    public static void addBananaToFruitsOrFruitsChildList(List<? extends JavaFruit> javaFruits) {
        //javaFruits.add(new JavaBanana());
    }

    /*
        La cosa curiosa, es que si tengo una lista de naranjas.
        No puedo enviar esa lista a una función de este estilo macedonia(List<Fruta> frutas) List<Naranja> no es un subtipo de List<Fruta>
        Pero si es un array si y da error en ejecucución
    */

    public static void main(String[] args) {
        addBanana(new JavaFruit[]{new JavaBanana()});
        addBanana(new JavaBanana[]{new JavaBanana()});
        addBanana(new JavaFruit[]{new JavaCherry()});
        //addBanana(new JavaCherry[]{new JavaCherry()}); Runtime error, program compiles...


        List<JavaFruit> fruitsWithBanana = new ArrayList<>();
        fruitsWithBanana.add(new JavaBanana());
        List<JavaBanana> bananas = new ArrayList<>();
        bananas.add(new JavaBanana());
        List<JavaFruit> fruitsWithCherry = new ArrayList<>();
        fruitsWithCherry.add(new JavaCherry());
        List<JavaCherry> cherries = new ArrayList<>();
        cherries.add(new JavaCherry());

        // This lines doesn't compile bcs List<JavaCherry> or List<JavaBanana> aren't subtypes from List<JavaFruit>
        addBananaToFruitsList(fruitsWithBanana);
        //addBananaToFruitsList(bananas);
        addBananaToFruitsList(fruitsWithCherry);
        //addBananaToFruitsList(cherries);


        addBananaToBananaOrFatherList(fruitsWithBanana);
        addBananaToBananaOrFatherList(bananas);
        addBananaToBananaOrFatherList(fruitsWithCherry);
        // addBananaToBananaOrFatherList(cherries); Cherry is not a banana neither a banana child

        addBananaToFruitsOrFruitsChildList(fruitsWithBanana);
        addBananaToFruitsOrFruitsChildList(bananas);
        addBananaToFruitsOrFruitsChildList(fruitsWithCherry);
        addBananaToFruitsOrFruitsChildList(cherries);

    }

}
