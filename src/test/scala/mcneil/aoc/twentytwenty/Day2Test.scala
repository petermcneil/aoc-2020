package mcneil.aoc.twentytwenty

class Day2Test extends Testing {
  private val day2: Seq[String] = loadPathToSeq("day2.txt")
  private val underTest = new Day2()

  test("How many passwords are correct to their policy?") {
    val result = underTest.part1(day2)

    assertResult(603)(result)
  }

  test("How many passwords contain the char at at least one index?") {
    val result = underTest.part2(day2)

    assertResult(404)(result)
  }


}
