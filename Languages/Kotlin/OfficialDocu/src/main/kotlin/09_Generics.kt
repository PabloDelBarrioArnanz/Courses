import java.util.ArrayList

// Also check playground.variance

// Generic In/Out/Where
class Box<T>(t: T)

val box1: Box<Int> = Box<Int>(1)
val box2 = Box(1)

// Variance
// Kotlin has no wildcard, but why Java does?
// Java uses wildcards to increase flexibility
// Bcs java type are invariant, so List<String> is not a subtype from List<Object>
// This is to avoid this code compiles and broke in runtime

/*
    List<String> strs = new ArrayList<String>();
    List<Object> objs = strs; // !!! A compile-time error here saves us from a runtime exception later.

*/

// Se another example
// If java addAll method signature would be like this
//     void addAll(Collection<E> items)

// Then you wouldn't can do this bcs Collection<String> is not a subtype from Collection<Object>, Do not confuse with add an item, this is list type not item type
/*
      void copyAll(Collection<Object> to, Collection<String> from) {
            to.addAll(from);
      }
*/
// The actual signature of addAll is
/*
     interface Collection<E> ... {
        void addAll(Collection<? extends E> items); // The wildcard type argument ? extends E indicates that this method accepts a collection of objects of E or a subtype of E, not just E itself
     }
*/
// Collection<String> is a subtype of Collection<? extends Object>.
// In other words, the wildcard with an extends-bound (upper bound) makes the type covariant.

// Other mechanism super
// If we have a list of string and get make a get we can read it as an object
// In the same way with the list if we want to put an object in the list we need
// List<? super String> read as strings or any of its supertypes
// This is called contravariance

// Summary
// Ihe wildcard with an extends-bound (upper bound) makes the type covariant List<? extends E>
// Ihe wildcard with a super-bound (bottom bound) makes the type contravariance List<? super E>

// In kotlin there is a way to make this List<? extends E> in a simple way with no adding complex
// Declaration-site playground.variance
class Source<out T>(val t: T) {
    // <? extends T>
    fun nextT(): T { // producer
        return t
    }
}

val s1 = Source(1)
val s2: Source<Any> = s1
// The out modifier is called a playground.variance annotation it provides declaration-site playground.variance
// In contrast with Java's use-site playground.variance where wildcards in the type usages make the types covariant
// In other words, you can say that the class C is covariant in the parameter T, or that T is a covariant type parameter
// You can think of Source as being a producer of T's, and NOT a consumer of T's.


// Also exists in annotation, witch makes a type parameter contravariant (can only be consumed and never produced)
interface Comparable<in T> {
    operator fun compareTo(other: T): Int // consumer
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, you can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x // OK!
}

// Type projections Use-site playground.variance
fun copy(from: Array<out Any>, to: Array<Any>) { // Array<? extends String>
}

val ints: Array<Int> = arrayOf(1, 2, 3)
val any = Array<Any>(3) { "" }
val copyValue = copy(ints, any)

fun fill(dest: Array<in String>, value: String) { // Array<? super String>

}

val dest1: Array<String> = arrayOf("")
val dest2: Array<Any> = arrayOf()

val fillValue1 = fill(dest1, "")
val fillValue2 = fill(dest2, "")

// Star-projections
// Function<*, String> means Function<in Nothing, String>.
// Function<Int, *> means Function<Int, out Any?>.
// Function<*, *> means Function<in Nothing, out Any?>

// Also can be used _ for automatically infer a type of the argument when other types are explicitly specified

// Also generic function can be used as extensions
fun <T> T.basicToString(): String { // extension function
    return ""
}

val basicString = Source(1).basicToString()

// Upper bounds if not specified is Any?
fun <T : Comparable<T>> sort(list: List<T>) {} // Any type witch implements Comparable
//val fail = sort(listOf(HashMap<Int, String>())) // Error: HashMap<Int, String> is not a subtype of Comparable<HashMap<Int, String>>


// If you need to define more than one restriction you can use where
// T type must implement both CharSequence and Comparable
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
        where T : CharSequence,
              T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}

// Type erasure
val mutableList = mutableListOf("")
val result = if (mutableList is List<*>) {
    mutableList.forEach { println(it) } // The items are typed as `Any?`
} else null

fun handleStrings(list: MutableList<String>) {
    if (list is ArrayList) {
        // `list` is smart-cast to `ArrayList<String>`
    }
}

// uncheck cast
fun readDictionary(file: String): Map<String, *> = mutableMapOf("" to "")

// Warning: Unchecked cast: `Map<String, *>` to `Map<String, Int>`
val intsDictionary: Map<String, Int> = readDictionary("ints.dictionary") as Map<String, Int>