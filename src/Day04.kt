private fun String.toRange(): Pair<IntRange, IntRange> {
  val splitted = this.split(",")

  return splitted[0].substringBefore("-").toInt()..splitted[0].substringAfter("-").toInt() to
    splitted[1].substringBefore("-").toInt()..splitted[1].substringAfter("-").toInt()
}

private infix fun IntRange.fullyContains(other: IntRange): Boolean {
  val intersection = (this intersect other)
  return intersection.size == this.count() || intersection.size == other.count()
}

private infix fun IntRange.overlaps(other: IntRange): Boolean {
  return (this intersect other).isNotEmpty()
}

fun main() {

  fun part1(input: List<String>): Int {
    return input.count {
      val (first, last) = it.toRange()
      first fullyContains last
    }
  }

  fun part2(input: List<String>): Int {
    return input.count {
      val (first, last) = it.toRange()
      first overlaps last
    }
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day04.test")
  check(part1(testInput) == 2)
  check(part2(testInput) == 4)

  val input = readInput("Day04")
  println(part1(input))
  println(part2(input))
}
