package mcneil.aoc.twentytwenty

class Day4Test extends Testing {
  //Part1
  private lazy val day4Example: Seq[String] = loadPathToSeq("day4-example.txt")

  //Part2
  private lazy val day4ExamplePart2: Seq[String] = loadPathToSeq("day4-example2.txt")
  private lazy val day4ExamplePart2Invalid: Seq[String] = loadPathToSeq("day4-example2-invalid.txt")

  private lazy val day4: Seq[String] = loadPathToSeq("day4.txt")

  private val underTest = new Day4()

  test("Example: Part 1 - Find 2 valid passports") {
    val result = underTest.part1(day4Example)

    assertResult(2)(result)
  }

  test("Part 1 - How valid passports are there?") {
    val result = underTest.part1(day4)

    assertResult(264)(result)
  }

  test("Example: Part 2 - 4 valid passports") {
    val result = underTest.part2(day4ExamplePart2)

    assertResult(4)(result)
  }

  test("Example: Part 2 - 4 invalid passports") {
    val result = underTest.part2(day4ExamplePart2Invalid)

    assertResult(0)(result)
  }


  test("Part 2 - How many valid passports are there?") {
    val result = underTest.part2(day4)

    assertResult(224)(result)
  }
}
