/**
 * This method looks terrible, needs a good refactoring!
 */
private fun createDirectoryMap(input: List<String>): Map<String, Int> {
  return buildMap {
    val directory = mutableListOf<String>()
    input.forEach {
      val splittedInput = it.split(" ")
      when (splittedInput.first()) {
        "$" -> when (splittedInput.last()) {
          ".." -> directory.removeLast()
          "ls" -> {}
          else -> {
            directory.add(it.substringAfterLast(" "))
            this[directory.joinToString("/")] = 0
          }
        }

        else -> if (it.first().isDigit()) {
          val tmp = directory.toMutableList()
          while (tmp.isNotEmpty()) {
            val directoryToString = tmp.joinToString("/")
            this[directoryToString] = this[directoryToString]!! + it.substringBefore(" ").toInt()
            tmp.removeLast()
          }
        }
      }
    }
  }
}

fun main() {

  fun part1(input: List<String>): Int {
    val map = createDirectoryMap(input)
    return map.values.filter { it < 100_000 }.sumOf { it }
  }

  fun part2(input: List<String>): Int {
    val map = createDirectoryMap(input)
    val used: Int = map["/"]!!
    return map.values.filter { (70_000_000 - (used - it)) >= 30_000_000 }.min()
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day07.test")
  check(part1(testInput) == 95437)
  check(part2(testInput) == 24933642)

  val input = readInput("Day07")
  println(part1(input))
  println(part2(input))
}
