package part4Implicits


//Type Class its a class witch specifies a set of operations that can be applied to a given type
object TypeClasses extends App {

  trait HTMLWritable {
    def toHTML: String
  }

  case class User(name: String, age: Int, email: String) extends HTMLWritable {
    override def toHTML: String = s"<div>$name ($age yo) <a href=$email/> </div>"
  }

  User("John", 32, "john@rockthejvm.com").toHTML
  /*
    1. For the types WE write (only works for User no java dates and more...)
    2. ONE implementation out of quite a number (we can need more than one impl for user logged...)
  */

  //Option 2 - PatternMatching
  object HTMLSerializerPM {
    def serializeToHtml(value: Any) = value match {
      case User(n, a, e) =>
      //case java.util.Date =>
      case _ =>
    }
  }

  /*
    1. Lost type -> Any
    2. Need to be updated every time we add some structure
    3. Still one implementation for structure
  */

  trait HTMLSerializer[T] {
    def serialize(value: T): String
  }

  object UserSerializer extends HTMLSerializer[User] {
    override def serialize(user: User): String = s"<div>${user.name} (${user.age} yo) <a href=${user.email}/> </div>"
  }

  val john = User("John", 32, "john@rockthejvm.com")
  println(UserSerializer.serialize(john))

  //1. We can define serializer for others types
  import java.util.Date
  object DateSerializer extends HTMLSerializer[Date] {
  override def serialize(date: Date): String = s"<div>${date.toString}/> </div>"
  }

  //2. We can define Multiple serializers
  object PartialUserSerializer extends HTMLSerializer[User] {
    override def serialize(user: User): String = s"<div>${user.name}/> </div>"
  }

  /*
    Exercise: Equality
  */

  trait Equal[T] {
    def apply(value1: T, value2: T): Boolean
  }

  object NameEquality extends Equal[User] {
    def apply(user1: User, user2: User): Boolean = user1.email == user2.email
  }

  object FullEquality extends Equal[User] {
    def apply(user1: User, user2: User): Boolean = user1.email == user2.email && user1.name == user2.name
  }

  val user1 = User("user1", 27, "user1@rockthejvm.com")
  val user2 = User("user2", 32, "user2@rockthejvm.com")

  NameEquality(user1, user2)

  //PART 2 - Implicits and TypeClasses
  object HTMLSerializer {
    def serialize[T](value: T)(implicit serializer: HTMLSerializer[T]): String = serializer.serialize(value)

    def apply[T](implicit serializer: HTMLSerializer[T]): HTMLSerializer[T] = serializer
  }

  implicit object IntSerializer extends HTMLSerializer[Int] {
    override def serialize(value: Int): String = s"<div style: color=blue>$value</div>"
  }

  implicit object UserSerializerImplicit extends HTMLSerializer[User] {
    override def serialize(user: User): String = s"<div>${user.name} (${user.age} yo) <a href=${user.email}/> </div>"
  }

  println(HTMLSerializer.serialize(42))
  println(HTMLSerializer.serialize(42)(value => s"<div style: color=red>$value</div>"))

  println(HTMLSerializer.serialize(john))
  //access to the entire type class interface (more method if have)
  println(HTMLSerializer[User].serialize(john)) //==> HTMLSerializer.apply(UserSerializerImplicit).serialize(john)


  //TYPE CLASS PATTERN
  trait MyTypeClassTemplate[T] {
    def action(value: T): String
  }

  object MyTypeClassTemplate {
    def apply[T](implicit instance: MyTypeClassTemplate[T]): MyTypeClassTemplate[T] = instance
  }

  //Exercise: implement the TC pattern for the Equality
  trait EqualTc[T] {
    def isEqual(value1: T, value2: T): Boolean
  }

  object EqualTc {

    def apply[T](implicit equal: EqualTc[T]): EqualTc[T] = equal

    def isEqual[T](value1: T, value2: T)(implicit equal: EqualTc[T]): Boolean = equal.isEqual(value1, value2)
  }

  implicit object UserEqualityTc extends EqualTc[User] {
    def isFullEqual(user1: User, user2: User): Boolean = user1.email == user2.email && user1.name == user2.name
    def isEqual(user1: User, user2: User): Boolean = user1.email == user2.email
  }

  EqualTc.isEqual(user1, user2)
  EqualTc[User].isEqual(user1, user2)
}
