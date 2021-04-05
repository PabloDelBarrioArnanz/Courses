package part3fp

object TuplesAndMaps extends App {

  //TUPLES
  val aTuple = Tuple2(2, "Scala")
  val anOtherTuple_1 = (2, "Scala")
  val anOtherTuple_2 = 2 -> "Scala"

  println(aTuple._1) //First element
  println(aTuple.copy(_2 = "Goodbye Java"))
  println(aTuple.swap)

  //MAPS
  val aMap: Map[String, Int] = Map()
  val phoneBook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)

  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  println(phoneBook("Mary")) //No element exception or withDefaultValue

  val newParing = "Mary" -> 678
  val newPhoneBook = phoneBook + newParing

  //map, flatMap, filter
  println(newPhoneBook.map(pair => pair._1.toLowerCase -> pair._2))
  println(newPhoneBook.view.filterKeys(_.startsWith("J")).toList)
  println(newPhoneBook.view.mapValues(_ + "-000").toList)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(_.charAt(0)))

  //Examples
  //1
  val testPhoneBook = Map("Jim" -> 555, "JIM" -> 789)
  println(testPhoneBook.map(pair => pair._1.toLowerCase -> pair._2)) //Overlap produces lose first key -> value

  //2
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    network + (a -> (network(a) + b) + b -> (network(b) + a))

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    network + (a -> (network(a) - b) + b -> (network(b) - a))

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network.view.mapValues(_.filterNot(_.equals(person))).toMap.removed(person)

  def removeRecursive(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if(friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    }
    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(_._2.size)._1

  def numberPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(_._2.isEmpty)

  def areConnected(network: Map[String, Set[String]], a: String, b: String): Boolean =
    network(a).contains(b) && network(b).contains(a)

  val network = Map[String, Set[String]] (
    "Pablo" -> Set("Juan", "Pedro", "Carlos", "Julian"),
    "Carlos" -> Set("Pablo", "Juan"),
    "Pedro" -> Set("Julian", "Juan", "Pablo"),
    "Julian" -> Set("Pedro", "Pablo"),
    "Juan" -> Set("Pedro", "Carlos", "Pablo"),
    "Jesus" -> Set.empty
  )
  println(remove(network, "Pablo"))
  println(numberPeopleWithNoFriends(network))
  println(areConnected(network, "Pablo", "Carlos"))
  println(areConnected(network, "Juan", "Julian"))
}
