private data class Move(val amount: Int, val from: Int, val to: Int) {

  fun execute1(stacks: List<MutableList<Char>>) {
    repeat(amount) { stacks[to].add(stacks[from].removeLast()) }
  }

  fun execute2(stacks: List<MutableList<Char>>) {
    repeat(amount) { stacks[to].add(stacks[from].removeMiddleTop(amount - it)) }
  }
}

private fun parseStacks(input: List<String>): List<MutableList<Char>> {
  val stackRows = input.takeWhile { it.contains('[') }
  return (1..stackRows.last().length step 4).map { index ->
    stackRows
      .mapNotNull { it.getOrNull(index) }
      .filter { it.isUpperCase() }
      .reversed()
      .toMutableList()
  }
}

private fun parseMoves(input: List<String>): List<Move> {
  return input
    .dropWhile { !it.startsWith("move") }
    .map { row ->
      row.split(" ").let { parts ->
        Move(parts[1].toInt(), parts[3].toInt() - 1, parts[5].toInt() - 1)
      }
    }

}

private fun <E> MutableList<E>.removeMiddleTop(i: Int): E {
  return removeAt((size) - i)
}

private fun List<MutableList<Char>>.topToString(): String {
  return joinToString("", transform = { it.last().toString() })
}

fun main() {


  fun part1(input: List<String>): String {
    val stacks = parseStacks(input)

    parseMoves(input)
      .forEach {
        it.execute1(stacks)
      }

    return stacks.topToString()
  }

  fun part2(input: List<String>): String {
    val stacks = parseStacks(input)

    parseMoves(input)
      .forEach {
        it.execute2(stacks)
      }

    return stacks.topToString()
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day05.test")
  check(part1(testInput) == "CMZ")
  check(part2(testInput) == "MCD")

  val input = readInput("Day05")
  println(part1(input))
  println(part2(input))
}
