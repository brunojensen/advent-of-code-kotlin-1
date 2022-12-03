private operator fun String.component1() = this.substring(0, this.length / 2)
private operator fun String.component2() = this.substring(this.length / 2)

private fun Char.priority(): Int = when (this) {
  in 'a'..'z' -> (this - 'a') + 1
  in 'A'..'Z' -> (this - 'A') + 27
  else -> error("out of the range: $this")
}

private fun Pair<String, String>.findDuplicatedChar(): Char {
  return (this.first.toSet() intersect this.second.toSet()).first()
}

private fun Triple<String, String, String>.findDuplicatedChar(): Char {
  return ((this.first.toSet() intersect this.second.toSet())
    intersect (this.third.toSet())).first()
}

fun main() {

  fun part1(input: List<String>): Int {
    return input
      .map {
        val (first, second) = it
        Pair(first, second).findDuplicatedChar()
      }.sumOf {
        it.priority()
      }
  }

  fun part2(input: List<String>): Int {
    return input
      .chunked(3) {
        val (first, second, third) = it
        Triple(first, second, third).findDuplicatedChar()
      }.sumOf {
        it.priority()
      }
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day03.test")
  check(part1(testInput) == 157)
  check(part2(testInput) == 70)

  val input = readInput("Day03")
  println(part1(input))
  println(part2(input))
}