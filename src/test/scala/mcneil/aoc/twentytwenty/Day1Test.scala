package mcneil.aoc.twentytwenty

class Day1Test extends Testing {
  private val day1: Seq[Int] = loadPathToSeq("day1.txt").map(_.toInt)
  private val underTest = new Day1()

  test("Find product of two numbers that add to make 2020") {
    val result = underTest.part1(day1)

    assertResult(290784)(result)
  }

  test("Find product of three numbers that add to make 2020") {
    val result = underTest.part2(day1)

    assertResult(177337980)(result)
  }

}
