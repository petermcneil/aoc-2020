package mcneil.aoc.twentytwenty

class Day5Test extends Testing {
  private val day5: Seq[String] = loadPathToSeq("day5.txt")
  private val underTest = new Day5()


  test("Part 1 - Example 1") {
    assertResult(567)(underTest.part1(Seq("BFFFBBFRRR")))
  }

  test("Part 1 - Example 2") {
    assertResult(119)(underTest.part1(Seq("FFFBBBFRRR")))
  }

  test("Part 1 - Example 3") {
    assertResult(820)(underTest.part1(Seq("BBFFBBFRLL")))
  }

  test("Part 1 - Example 4") {
    assertResult(820)(underTest.part1(Seq("BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL")))
  }

  test("Part 1 - What is the highest ID seat?") {
    val result = underTest.part1(day5)

    assertResult(835)(result)
  }

  test("Part 2 - Where is my seat?") {
    val result = underTest.part2(day5)

    assertResult(649)(result)
  }
}
