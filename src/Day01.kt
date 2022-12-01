import java.util.PriorityQueue

fun PriorityQueue<Int>.offerAndPollIfMaxSize(input: Int, maxSizw: Int) {
  this.offer(input)
  if (this.size > maxSizw) {
    this.poll()
  }
}

fun main() {

  fun part1(input: List<String>): Int {
    var sum = 0
    var max = 0
    for (i in input) {
      if (i.isBlank()) {
        sum = 0
        continue
      }
      sum += i.toInt()
      max = Math.max(sum, max)
    }
    return max
  }

  fun part2(input: List<String>): Int {
    val queue: PriorityQueue<Int> = PriorityQueue()
    var sum = 0
    for (i in input) {
      if (i.isBlank()) {
        queue.offerAndPollIfMaxSize(sum, 3)
        sum = 0
        continue
      }
      sum += i.toInt()
    }
    queue.offerAndPollIfMaxSize(sum, 3)
    return queue.sum()
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day01.test")
  check(part1(testInput) == 24000)
  check(part2(testInput) == 45000)

  val input = readInput("Day01")
  println(part1(input))
  println(part2(input))
}
