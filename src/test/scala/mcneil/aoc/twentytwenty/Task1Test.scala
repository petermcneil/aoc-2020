package mcneil.aoc.twentytwenty

class Task1Test extends Testing {
  private val task1: Seq[Int] = loadPathToSeq("task1.txt").map(_.toInt)
  private val underTest = new Task1()

  test("Find product of two numbers that add to make 2020") {
    val result = underTest.part1(task1)

    assertResult(result)(290784)
  }

  test("Find product of three numbers that add to make 2020") {
    val result = underTest.part2(task1)

    assertResult(result)(177337980)
  }

}
