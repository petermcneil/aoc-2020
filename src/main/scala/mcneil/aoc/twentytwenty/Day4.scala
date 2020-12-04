package mcneil.aoc.twentytwenty


import scala.annotation.tailrec
import scala.util.matching.Regex
import scala.util.{Failure, Success, Try}

class Day4 {
  type Passport = Map[String, String]
  lazy val validKeys = Seq("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt")

  //Part 2
  lazy val hgtR: Regex = "(\\d+)(in|cm)".r
  lazy val hclR: Regex = "#([a-z0-9]{6})".r
  lazy val pidR: Regex = "([0-9]{9})".r
  lazy val eclS = Seq("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

  def part1(lines: Seq[String]): Int = {


    @tailrec
    def recursePart1(lines: Seq[String], counter: Int = 0): Int = {
      lines match {
        case Nil => counter
        case l =>
          val (newLines, passport) = extractPassport(l)

          if (hasAllKeys(passport)) {
            recursePart1(newLines, counter + 1)
          } else {
            recursePart1(newLines, counter)
          }
      }

    }

    recursePart1(lines)
  }

  def part2(lines: Seq[String]): Int = {
    def isPassportValid(passport: Passport): Boolean = {
      if (passport.isEmpty) {
        false
      } else {
        if (hasAllKeys(passport)) {

          def betweenTwo(value: String, bottom: Int, top: Int): Boolean = {
            Try(value.toInt) match {
              case Failure(_) => false
              case Success(x) =>
                (x >= bottom && x <= top)
            }
          }

          val byr = betweenTwo(passport("byr"), 1920, 2002)
          val iyr = betweenTwo(passport("iyr"), 2010, 2020)
          val eyr = betweenTwo(passport("eyr"), 2020, 2030)
          val hgt = passport("hgt") match {
            case hgtR(height, unit) =>
              if (unit == "cm") {
                betweenTwo(height, 150, 193)
              } else {
                betweenTwo(height, 59, 76)
              }
            case _ => false
          }
          val hcl = hclR.matches(passport("hcl"))
          val ecl = eclS.contains(passport("ecl"))
          val pid = pidR.matches(passport("pid"))

          byr && iyr && eyr && hgt && hcl && ecl && pid

        } else {
          false
        }
      }
    }

    @tailrec
    def recursePart2(lines: Seq[String], counter: Int = 0): Int = {
      lines match {
        case Nil => counter
        case l =>
          val (newLines, passport) = extractPassport(l)

          if (isPassportValid(passport)) {
            recursePart2(newLines, counter + 1)
          } else {
            recursePart2(newLines, counter)
          }
      }

    }

    recursePart2(lines)
  }


  private def extractLine(line: String): Map[String, String] = {
    line.split(" ")
      .map(f => {
        val s"$key:$value" = f
        key -> value
      }).toMap
  }

  private def extractPassport(lines: Seq[String]): (Seq[String], Passport) = {

    @tailrec
    def recurseForPassport(lines: Seq[String], passport: Passport = Map.empty): (Seq[String], Passport) = {
      lines match {
        case Nil => (lines, passport)
        case x :: xs =>
          if (x == "") {
            (xs, passport)
          } else {
            val updatedPassport: Map[String, String] = passport ++ extractLine(x)
            recurseForPassport(xs, updatedPassport)
          }
      }
    }

    recurseForPassport(lines)
  }

  private def hasAllKeys(passport: Passport): Boolean = {
    if (passport.isEmpty) {
      false
    } else {
      validKeys.forall(passport.contains)
    }
  }
}
