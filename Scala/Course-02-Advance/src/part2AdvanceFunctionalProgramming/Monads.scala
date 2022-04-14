package part2AdvanceFunctionalProgramming

/*Monads are a kind of types witch has some fundamental ops
  unit/apply/pure -> unit(value: A): Monad[A]
  flatmap/bind    -> flatMap(f: A => Monad[B]): Monad[B]

  Examples -> List, Option, Try, Future, Stream, Set...

  Must satisfies some laws:
    - left identity  -> unit(x).flatMap(f)  == f(x) //Introducir un valor en una monada y aplicar una funcion con el flatMap a la monada tiene que ser igual a aplicar la función al valor
    - right identity -> monad.flatMap(unit) == monad //Si a una monada la aplicamos el flatmap con la funcion de crear una monada devuelve la misma monada
    - associative    -> monad.flatMap(f).flatMap(g) == monad.flatMap(x => f(x).flatMap(g)) // Aplicar dos funciones seguidas con flatMap a un monada es igual a aplicar dentro de un flatMap una funcion y otro flatMap seguido

  Example List
    - lest identity  -> List(x).flatMap(f) = f(x) ++ Nil.flatMap(f) = f(x)
    - right identity -> list.flatMap(x => List(x)) = list
    - associative    -> [a b c].flatMap(f).flatMap(g) = (f(a) ++ f(b) ++ f(c)).flatMap(g) = f(a).flatMap(g) ++ f(b).flatMap(g) ++ f(c).flatMap(g)
                                                        [a b c].flatMap(f(_).flatMap(g)) ==
                                                        [a b c].flatMap(x => f(x).flatMap(g))

  Example Option //focus some case
    - left identity  -> Option(x).flatMap(f) = Some(x).flatMap(f) = f(x)
    - right identity -> opt.flatMap(x => Option(x)) = opt
    - associative    -> opt.flatMap(f).flatMap(g)        =     opt.flatMap(x => f(x).flatMap(g)) =
                        Some(v).flatMap(f).flatMap(g)          Some(v).flatMap(x => f(x).flatMap(g)) =
                        f(v).flatMap(g)                  =     f(v).flatMap(g)

  Monads with map and flatten in terms of flatMap

  Monad[T] {
    def flatMap[B](f: A => Monad[B]): Monad[B] = .. implemented

    def map[B](f: A => B): Monad[B] = flatMap(x => unit(f(x))) //Monad[B]
    def flatten(m: Monad[Monad[T]]): Monad[T] = m.flatMap((x => x))
  }
  List(1,2,3).map(_ * 2) = List(1,2,3).flatMap(x => List(x * 2))
  List(List(1,2),List(1,2,3)).flatten = List(List(1,2),List(1,2,3)).flatMap(x => x)
*/

object Monads extends App {

  trait Attempt[+A] {
    def flatMap[B](f: A => Attempt[B]): Attempt[B]
    def map[B](f: A => B): Attempt[B] = flatMap(x => Attempt(f(x)))
    def flatten[B](attempt: Attempt[Attempt[B]]): Attempt[B] = attempt.flatMap(attempt => attempt)
  }
  object Attempt {
    //Instance of receive an object A we receive the function to obtain a, that way we prevent exceptions
    def apply[A](a: => A): Attempt[A] = try {
      Success(a)
    } catch {
      case e: Throwable => Fail(e)
    }
  }

  case class Success[+A](value: A) extends Attempt[A] {
    override def flatMap[B](f: A => Attempt[B]): Attempt[B] = try {
      f(value)
    } catch {
      case e: Throwable => Fail(e)
    }
  }

  case class Fail(exception: Throwable) extends Attempt[Nothing] {
    override def flatMap[B](f: Nothing => Attempt[B]): Attempt[B] = this
  }

  /*Left Identity
    unit.flatMap(f) == f(x)
    Attempt(x).flatMap(f) = Success(x).flatMap(f) == f(x)

    Right identity
    attempt.flatMap(unit) == attempt
    Attempt(x).flatMap(unit) = Success(x).flatMap(unit) == unit
    Fail(e).flatMap(unit) == Fail(e)

    Associative
    attempt.flatMap(f).flatMap(g) == attempt.flatMap(x => f(x).flatMap(g))
    attempt.flatMap(f).flatMap(g) = Success(x).flatMap(f).flatMap(g) = f(x).flatMap(g) ==
    attempt.flatMap(x => f(x).flatMap(g)) = Success(x).flatMap(x => f(x).flatMap(g)) = f(x).flatMap(g)
  */

  val attemptFail = Attempt {throw new RuntimeException("My own monad!")}
  println(attemptFail) //Fail (RuntimeException: My..)
  val successFromFail = Attempt(attemptFail)
  println(successFromFail) //Success(Fail (RuntimeException: My..))
  println(successFromFail.flatten(successFromFail)) //Fail (RuntimeException: My..))

  val attemptSuccess: Attempt[Int] = Attempt(1).flatMap(s => Attempt(s + 1)).flatMap(s => Attempt(s + 1)).map(_ + 1)
  println(attemptSuccess)
}
