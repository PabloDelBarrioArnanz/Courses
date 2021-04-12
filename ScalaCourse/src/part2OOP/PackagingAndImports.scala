package part2OOP

import part1Basics.{CBNvsCBV, Functions}

import java.{sql, util}
//import all => import part1Basics._

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  //Dentro del mismo paquete, se accede por nombre
  val person = new Person()

  //import de package
  val function1 = Functions
  val function2 = part1Basics.Functions

  //package object
  //1 por paquete es accesible en to-do el paquete
  sayHello()

  /*
  val utilDate = new Date()
  val sqlDate = sql.Date.valeOf("")
   */
  //OR change Name

  val utilDate = new Date()
  val sqlDate = SqlDate.valueOf("")
}
