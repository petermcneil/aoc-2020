package mcneil.aoc.twentytwenty

import scala.annotation.tailrec

class Day2 {

  def part1(lines: Seq[String]): Int = {

    @tailrec
    def countPasswords(lines: Seq[String], counter: Int = 0): Int = {
      lines match {
        case Nil => counter
        case line :: xs =>
          val (password, char, start, end) = extractLine(line)
          val range = Range(start.toInt, end.toInt + 1)
          val count = password.count(_ == char)

          if (range.contains(count)) {
            countPasswords(xs, counter + 1)
          } else {
            countPasswords(xs, counter)
          }
      }
    }

    countPasswords(lines)
  }

  def part2(lines: Seq[String]): Int = {
    @tailrec
    def charAtPasswords(lines: Seq[String], counter: Int = 0): Int = {
      lines match {
        case Nil => counter
        case line :: xs =>
          val (password, char, start, end) = extractLine(line)

          def boolForChatAt(oldPos: Int): Boolean= {
            val pos = oldPos -1

            if (pos < password.length && pos >= 0) {
              password.charAt(pos) == char
            } else {
              false
            }
          }

          val startPos = boolForChatAt(start)
          val endPos = boolForChatAt(end)

          if ((startPos || endPos) && !(startPos && endPos)) {
            charAtPasswords(xs, counter + 1)
          } else {
            charAtPasswords(xs, counter)
          }
      }
    }

    charAtPasswords(lines)
  }

  private def extractLine(line: String): (String, Char, Int, Int) = {
    val s"$start-$end $charString: $password" = line

    val char: Char = charString.toCharArray.head

    (password, char, start.toInt, end.toInt)
  }
}
