package part2AdvanceFunctionalProgramming.exercices


//Exercise: Implement a Lazy[T] monad = computation which will only be executed when it's needed
object LazyMonad extends App {

  class Lazy[+A](value: => A) {
    private lazy val internalValue = value
    //receive the parameter by name to avoid be executed
    def flatMap[B](f: (=> A) => Lazy[B]): Lazy[B] = f(internalValue)
  }
  object Lazy {
    def apply[A](value: => A): Lazy[A] = new Lazy(value)
  }

  val lazyInstance = Lazy {
    println("Hello scala")
    42
  }

  val flatMappedInstance1 = lazyInstance.flatMap(x => Lazy(x * 10))
  val flatMappedInstance2 = lazyInstance.flatMap(x => Lazy(x * 10))
}
