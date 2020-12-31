package part1Basics

import scala.annotation.tailrec

object Recursion extends App {

  def recursiveFactorial(number: Long): Long = {
    if (number <= 2) number
    else number * recursiveFactorial(number - 1)
  }

  //Tail recursion is better than recursion
  //Only needs de last stack frame
  def recursiveTailFactorial(number: Long): Long = {
    @tailrec
    def factorialHelper(time: Long, accumulator: Long): Long = {
      if (time <= 1) accumulator
      else factorialHelper(time - 1, time * accumulator)
    }

    factorialHelper(number, 1)
  }

  @tailrec
  def recursiveTailConcat(toRepeat: String, times: Long, accumulator: String): String = {
    if (times < 1) accumulator
    else recursiveTailConcat(toRepeat, times - 1, accumulator + toRepeat)
  }

  def recursiveFibonacci(number: Long): Long = {
    if (number <= 2) 1
    else recursiveFibonacci(number - 1) + recursiveFibonacci(number - 2)
  }

  def recursiveTailFibonacci(number: Long): Long = {
    @tailrec
    def fibonacciHelper(accumulator: Long, times: Long): Long = {
      if (times > number) accumulator
      else fibonacciHelper(times + accumulator, times + 1)
    }

    fibonacciHelper(0, 1)
  }
}

//fibonacci(5) = 1 + 2 + 3 + 4 + 5
