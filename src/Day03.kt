fun main() {

  operator fun String.component1() = this.substring(0, this.length / 2)
  operator fun String.component2() = this.substring(this.length / 2)

  operator fun List<String>.component1() = this[0]
  operator fun List<String>.component2() = this[1]
  operator fun List<String>.component3() = this[2]

  fun Char.priority(): Int {
    return when (this) {
      lowercaseChar() -> (this - 'a') + 1
      uppercaseChar() -> (this - 'A') + 27
      else -> 0
    }
  }

  fun Pair<String, String>.findDuplicated(): Char {
    return this.first.toSet().intersect(this.second.toSet()).first()
  }

  fun Triple<String, String, String>.findDuplicated(): Char {
    return this.first.toSet()
      .intersect(this.second.toSet())
      .intersect(this.third.toSet()).first()
  }

  fun part1(input: List<String>): Int {
    return input
      .map {
        val (first, second) = it
        Pair(first, second)
      }.sumOf {
        val duplicated = it.findDuplicated()
        duplicated.priority()
      }
  }

  fun part2(input: List<String>): Int {
    return input
      .chunked(3) {
        val (first, second, third) = it
        Triple(first, second, third)
      }.sumOf {
        val duplicated = it.findDuplicated()
        duplicated.priority()
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
