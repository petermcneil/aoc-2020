package mcneil.aoc.twentytwenty

import scala.annotation.tailrec

class Day5 {

  def part1(lines: Seq[String]): Int = {
    @tailrec
    def findHighestId(lines: Seq[String], highestId: Int = 0): Int = {
      lines match {
        case Nil => highestId
        case x::xs =>
          val row = findRow(x)
          val column = findColumn(x)
          val id = (row*8) + column

          if (id > highestId) {
            findHighestId(xs, id)
          } else {
            findHighestId(xs, highestId)
          }
      }

    }


    findHighestId(lines)
  }

  def part2(lines: Seq[String]): Int = {

    @tailrec
    def findAllTakenSeats(lines: Seq[String], output: Seq[Int] = Seq.empty): Seq[Int] = {
      lines match {
        case Nil => output
        case x::xs =>
          val row = findRow(x)
          val column = findColumn(x)
          val id = (row*8) + column
          findAllTakenSeats(xs, output ++ Seq(id))
      }
    }

    @tailrec
    def findMissingSeat(seats: Seq[Int], lastId: Int): Int = {
      if (seats.nonEmpty) {
        if ((seats.head - lastId) != 1) {
          lastId + 1
        } else {
          findMissingSeat(seats.tail, seats.head)
        }
      } else {
        lastId
      }
    }
    val takenSeats = findAllTakenSeats(lines).sorted

    findMissingSeat(takenSeats.tail, takenSeats.head)
  }

  def findRow(input: String, rows: Seq[Int] = 0 to 127): Int = {
    if (input.nonEmpty) {
      val split = rows.splitAt(rows.length / 2)
      input.charAt(0) match {
        case 'F' =>
          findRow(input.substring(1), split._1)
        case 'B' =>
          findRow(input.substring(1), split._2)
        case c =>
          rows.head
      }
    } else {
      rows.head
    }
  }

  def findColumn(input: String, columns: Seq[Int] = 0 to 7): Int = {
    if (input.nonEmpty) {
      val split = columns.splitAt(columns.length / 2)
      input.charAt(0) match {
        case 'R' =>
          findColumn(input.substring(1), split._2)
        case 'L' =>
          findColumn(input.substring(1), split._1)
        case c =>
          findColumn(input.substring(1), columns)
      }
    } else {
      if (columns.isEmpty) {
        0
      } else {
        columns.head
      }
    }
  }

}
