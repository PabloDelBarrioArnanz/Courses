// import java.util.Optional as op también se puede añadir alias a los imports

typealias MyMapList = MutableMap<Int, ArrayList<String>>
typealias MyFun = (Int, String, MyMapList) -> Boolean
typealias MyNestedClass = MyBaseClass.MyNestedChildClass
// Solo funciona con nested no con inner porque habría que instanciar la clase padre

fun main() {

    val myMap1: MutableMap<Int, ArrayList<String>> = mutableMapOf()
    val myMap2: MyMapList = mutableMapOf()

    val myAwesomeFun: MyFun = { number, text, map ->
        map[number] = arrayListOf(text)
        true
    }

    val myNestedClass1 = MyBaseClass.MyNestedChildClass()
    val myNestedClass2 = MyNestedClass()

    MyBaseClass().MyInnerChildClass()

}

class MyBaseClass {
    class MyNestedChildClass {

    }
    inner class MyInnerChildClass {

    }
}

