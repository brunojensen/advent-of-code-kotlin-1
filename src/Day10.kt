private val strings = readInput("Day10.test")

fun main() {

  fun sumSixSignalStrengths(cycle : Int, register : Int) =
    if(cycle % 40 == 20) cycle * register else 0

  fun part1(input: List<String>): Long {
    var cycle = 0
    var sumSixSignalStrengths = 0L
    var register = 1

    input.forEach {
      sumSixSignalStrengths += sumSixSignalStrengths(++cycle, register)
      if("noop" != it) {
        sumSixSignalStrengths += sumSixSignalStrengths(++cycle, register)
        register += it.split(" ")[1].toInt()
      }
    }
    return sumSixSignalStrengths
  }

  fun part2(input: List<String>): Long {
    return 0
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day10.test")
  check(part1(testInput) == 13140L)
  check(part2(testInput) == 0L)

  val input = readInput("Day10")
  println(part1(input))
  println(part2(input))
}
