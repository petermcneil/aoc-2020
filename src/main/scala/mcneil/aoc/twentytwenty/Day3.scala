package mcneil.aoc.twentytwenty

import scala.annotation.tailrec

class Day3 {

  def part1(lines: Seq[String]): Int = {
    val lineLength = lines.head.length

    @tailrec
    def threeRightAndDownOne(seq: Seq[String], treeCounter: Int = 0, position: Int = 0): Int = {
      val newPos = (position + 3 % lineLength)
      seq match {
        case Nil => treeCounter
        case _ :: y :: xs =>
          val charAt3Across = y.charAt(newPos)
          if (charAt3Across == '#') {
            threeRightAndDownOne(y :: xs, treeCounter + 1, newPos)
          } else {
            threeRightAndDownOne(y :: xs, treeCounter, newPos)
          }
        case _ => treeCounter
      }
    }

    threeRightAndDownOne(lines)
  }

  def part2(lines: Seq[String]): Long = {
    val lineLength = lines.head.length


    @tailrec
    def treesOnASlope(seq: Seq[String], right: Int, down: Int, position: Int = 0, treeCounter: Long = 0): Long = {
      val newPos = (position + right) % lineLength
      seq match {
        case Nil => treeCounter
        case _ :: xs =>
          val passItOn = if (down == 1) xs else xs.takeRight(xs.length - 1)
          passItOn match {
            case Nil => treeCounter
            case _ =>
              val y = passItOn.head
              val charAt3Across = y.charAt(newPos)
              if (charAt3Across == '#') {
                treesOnASlope(passItOn, right, down, treeCounter = treeCounter + 1, position = newPos)
              } else {
                treesOnASlope(passItOn, right, down, treeCounter = treeCounter, position = newPos)
              }
          }

        case _ => treeCounter
      }
    }

    val r1d1 = treesOnASlope(lines, 1, 1)
    val r3d1 = treesOnASlope(lines, 3, 1)
    val r5d1 = treesOnASlope(lines, 5, 1)
    val r7d1 = treesOnASlope(lines, 7, 1)
    val r1d2 = treesOnASlope(lines, 1, 2)


    r1d1 * r3d1 * r5d1 * r7d1 * r1d2
  }
}
