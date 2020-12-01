package mcneil.aoc.twentytwenty

import scala.annotation.tailrec

class Task1 {

  def part1(numbers: Seq[Int]): Int = {
    val res = findSumOfTwo(numbers)
    res._1 * res._2
  }

  def part2(numbers:Seq[Int]): Int = {
    val res = findSumOfThree(numbers)
    res._1 * res._2 *res._3
  }

  private def findSumOfThree(numbers: Seq[Int]): (Int, Int, Int) = {

    @tailrec
    def tailAgain(head: Int, x: Int, tail: Seq[Int]): Option[(Int, Int, Int)] = {
      tail match {
        case Nil => None
        case y::ys =>
          val res = x + y + head
          if(res == 2020) {
            Some(x, head, y)
          } else {
            tailAgain(head, x, ys)
          }
      }
    }

    @tailrec
    def tailToFindSum(head: Int, tail: Seq[Int]): Option[(Int, Int, Int)] ={
      tail match {
        case Nil => None
        case x::xs => tailAgain(head, x, tail) match {
          case Some(value) => Some(value)
          case None => tailToFindSum(head, xs)
        }
      }
    }

    @tailrec
    def recurseToFindAMatch(numbers: Seq[Int]): (Int, Int, Int) = {
      numbers match {
        case Nil => (0,0, 0)
        case x::xs => tailToFindSum(x, xs) match {
          case None => recurseToFindAMatch(xs)
          case Some(y) => y
        }
      }
    }

    recurseToFindAMatch(numbers)
  }

  private def findSumOfTwo(numbers: Seq[Int]): (Int, Int) = {
    @tailrec
    def tailToFindSum(head: Int, tail: Seq[Int]): Option[(Int, Int)] ={
      tail match {
        case Nil => None
        case x::xs =>
          val res = x + head
          if(res == 2020) {
            Some(x, head)
          } else {
            tailToFindSum(head, xs)
          }
      }
    }

    @tailrec
    def recurseToFindAMatch(numbers: Seq[Int]): (Int, Int) = {
      numbers match {
        case Nil => (0,0)
        case x::xs => tailToFindSum(x, xs) match {
          case None => recurseToFindAMatch(xs)
          case Some(y) => y
        }
      }
    }

    recurseToFindAMatch(numbers)
  }

}
