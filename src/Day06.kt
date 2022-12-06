// first implementation
private fun String.startOfPacketMarker1(size: Int): Int {
  var index = 0
  while ((index + size) < length - 1) {
    val subSequence = subSequence(index, index + size)
    if (subSequence.countDistinct() == size) {
      return index + size
    }
    index++
  }
  return -1
}

// Using kotlin standard library
private fun String.startOfPacketMarker2(size: Int): Int = windowedSequence(size)
  .indexOfFirst { it.countDistinct() == size } + size

private fun CharSequence.countDistinct(): Int {
  val set = HashSet<Char>()
  set.addAll(asIterable())
  return set.size
}

fun main() {

  fun part1(input: String): Int {
    return input.startOfPacketMarker1(4)
  }

  fun part2(input: String): Int {
    return input.startOfPacketMarker2(14)
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day06.test").first()
  check(part1(testInput) == 10)
  check(part2(testInput) == 29)

  val input = readInput("Day06").first()
  println(part1(input))
  println(part2(input))
}
