fun main() {

    val myNestedClass = BaseClass.MyNestedClass()
    val nestedSum = myNestedClass.sum(10, 5)
    println(nestedSum)

    val myInnerClass = BaseClass().MyInnerClass() // en este caso hay que crear la clase base
    val innerSum = myInnerClass.sumOne(6)
    println(innerSum)
}

class BaseClass {

    private val one = 1

    class MyNestedClass {
        fun sum(first: Int, second: Int): Int {
            return first + second // + one no puede acceder a atributos privados
        }
    }

    inner class MyInnerClass {
        fun sumOne(number: Int): Int {
            return number + one // Como es inner class si puede accerder aunque se privado
        }
    }
}


// En java si declaramos la clase interior como static sería nested y si no inner pocas veces se usan inner

/*
    public class MyClasses {
        private final String name = "pablo";

        public static class JavaNestedClass {
            public static void printSomething() {
                System.out.println("Hola "); // No puede acceder a name
            }
        }
        public class JavaInnerClass {
            public void printSomething() {
                System.out.println("Hola " + name);
            }
        }
    }

------------------------------------------------------------------------------------
    public static void main(String[] args) {
        MyClasses my = new MyClasses();

        //new MyClasses.JavaNestedClass().printSomething();
        MyClasses.JavaNestedClass.printSomething();


        MyClasses.JavaInnerClass innerClass = my.new JavaInnerClass();
        innerClass.printSomething();
    }

*/