package part1Basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase)
  println(str.length)

  //Scala specific
  val numberString = "45"
  val number = numberString.toInt
  println('a' +: numberString :+ 'z')
  println(str.reverse)
  println(str.take(2))

  //S-Interpolator
  val name = "Pablo"
  val age = 23
  //Solo funciona con la s delante del String
  val greeting1 = s"Hello, my name is $name and i am $age years old"
  val greeting2 = s"Hello, my name is $name and i am ${age + 1} years old"

  //F-interpolator
  val speed = 234341.2f
  //%X.Yf -> x = characteres en total como minimo
  //         y = charecteres precision decimal
  val myth = f"$name%s can eat $speed%1.5f burgers per minute"
  println(myth)

  //raw interpolator
  println("this is a \n new line")
  println(raw"this is a \n new line")
  println(raw"$age")
}
