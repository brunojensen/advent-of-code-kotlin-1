fun main() {
  
  fun part1(input: List<String>): Int {
    val matrix = input.map { it.map { v -> v.digitToInt() } }
    val visible = Array(matrix.size) { BooleanArray(matrix[0].size) }
    val edgesCount = (matrix.size * 2 + matrix[0].size * 2) - 4

    for (x in 1..matrix.size - 2) {
      for (y in 1..matrix[0].size - 2) {
        val curr = matrix[x][y]
        print(curr)
      }
      println()
    }

    return edgesCount + visible.sumOf { it.count { v -> v } }
  }

  fun part2(input: List<String>): Int {
    return 0
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day08.test")
  check(part1(testInput) == 21)
  check(part2(testInput) == 0)

  val input = readInput("Day08")
  println(part1(input))
  println(part2(input))
}
