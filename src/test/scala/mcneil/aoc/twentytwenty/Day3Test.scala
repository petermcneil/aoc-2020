package mcneil.aoc.twentytwenty

class Day3Test extends Testing {
  private lazy val day3Example: Seq[String] = loadPathToSeq("day3-example.txt")
  private lazy val day3: Seq[String] = loadPathToSeq("day3.txt")
  private val underTest = new Day3()

  test("Example: Part 1 - Hit 7 trees on the way down the slope") {
    val result = underTest.part1(day3Example)

    assertResult(7)(result)
  }

  test("Part 1 - How many trees would you hit on the toboggan on the way down?") {
    val result = underTest.part1(day3)

    assertResult(268)(result)
  }

  test("Example: Part 2 - Hit 7 trees on the way down the slope") {
    val result = underTest.part2(day3Example)

    assertResult(336)(result)
  }

  test("Part 2 - What is the product of the trees you hit on the toboggan on the way down for these slopes?") {
    val result = underTest.part2(day3)

    assertResult(3093068400L)(result)
  }
}
