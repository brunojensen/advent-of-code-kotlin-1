
enum class Play(val point: Int, val beats: Play? = null, val loses: Play? = null) {
  // Rock, Paper, Scissor
  A(0),
  B(0),
  C(0),
  X(1, C, B),
  Y(2, A, C),
  Z(3, B, A);
}

fun game1(his: Play, mine: Play): Int {
  return when (his) {
    mine.beats -> mine.point + 6
    mine.loses -> mine.point + 0
    else -> mine.point + 3
  }
}

fun game2(his: Play, mine: Play): Int {
  return when (mine) {
    Play.X -> Play.values().filter { null != it.loses }.first { it.loses == his }.point + 0
    Play.Z -> Play.values().filter { null != it.beats }.first { it.beats == his }.point + 6
    Play.Y -> Play.values().filter { null != it.beats && null != it.loses }.first { it.beats != his && it.loses != his }.point + 3
    else -> 0
  }
}

fun main() {
  operator fun String.component1() = this[0].toString()
  operator fun String.component2() = this[1].toString()
  operator fun String.component3() = this[2].toString()
  
  fun part1(input: List<String>): Int {
    return input
      .sumOf {
        val (first, _, last) = it
        game1(Play.valueOf(first), Play.valueOf(last))
      }
  }

  fun part2(input: List<String>): Int {
    return input
      .sumOf {
        val (first, _, last) = it
        game2(Play.valueOf(first), Play.valueOf(last))
      }
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day02.test")
  check(part1(testInput) == 15)
  check(part2(testInput) == 12)

  val input = readInput("Day02")
  println(part1(input))
  println(part2(input))
}
