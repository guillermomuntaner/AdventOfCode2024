fun main() {
    val day = "11"

    fun parseInput(input: List<String>) = input.first().split(" ").map { it.toLong() }

    fun part1(input: List<String>, iterations: Int): Int = (1..iterations)
        .fold(parseInput(input)) { list, _ ->
            list.flatMap { value ->
                when {
                    value == 0L -> listOf(1L)
                    value.toString().length % 2 == 0 -> {
                        val string = value.toString()
                        val middle = string.length / 2
                        listOf(string.substring(0, middle).toLong(),  string.substring(middle).toLong())
                    }
                    else -> listOf(value * 2024L)
                }
            }
        }
        .count()

    fun part2(input: List<String>, iterations: Int): Long = (1..iterations)
        .fold(
            parseInput(input)
                .groupingBy { it }
                .eachCount()
                .mapValues { (_, value) -> value.toLong() }
        ) { map, _ ->
            val newMap = mutableMapOf<Long, Long>()
            map.forEach { (value, count) ->
                when {
                    value == 0L -> {
                        newMap[1L] = (newMap[1L] ?: 0) + count
                    }
                    value.toString().length % 2 == 0 -> {
                        val string = value.toString()
                        val middle = string.length / 2
                        val leftValue = string.substring(0, middle).toLong()
                        val rightValue = string.substring(middle).toLong()
                        newMap[leftValue] = (newMap[leftValue] ?: 0) + count
                        newMap[rightValue] = (newMap[rightValue] ?: 0) + count
                    }
                    else -> {
                        newMap[value * 2024L] = (newMap[value * 2024L] ?: 0) + count
                    }
                }
            }
            newMap
        }
        .values
        .sum()

    val testInput = readInput("Day${day}_test")
    part1(testInput, iterations = 6).checkEqualTo(22)
    part2(testInput, iterations = 6).checkEqualTo(22L)
    part1(testInput, iterations = 25).checkEqualTo(55312)
    part2(testInput, iterations = 25).checkEqualTo(55312L)

    val input = readInput("Day${day}")
    part1(input, iterations = 25).also { it.println() }.checkEqualTo(194782)
    part2(input, iterations = 25).also { it.println() }.checkEqualTo(194782L)
    part2(input, iterations = 75).also { it.println() }.checkEqualTo(233007586663131L)
}
