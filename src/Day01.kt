import kotlin.math.abs

fun main() {
    fun parseInput(input: List<String>) = input
        .map { line -> line.split("   ").map { it.toInt() } }
        .map { it[0] to it[1] }
        .unzip()

    fun part1(input: List<String>): Int = parseInput(input)
            .let { it.first.sorted().zip(it.second.sorted()) }
            .sumOf { abs(it.first - it.second) }

    fun part2(input: List<String>): Int = parseInput(input)
            .let {
                it.first.sumOf { locationId -> locationId * it.second.count { it == locationId } }
            }

    val testInput = readInput("Day01_test")
    part1(testInput).checkEqualTo(11)
    part2(testInput).checkEqualTo(31)

    val input = readInput("Day01")
    part1(input).checkEqualTo(2769675)
    part2(input).checkEqualTo(24643097)
}
