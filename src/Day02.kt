import kotlin.math.abs
import kotlin.math.sign

fun main() {
    val day = "02"

    fun parseInput(input: List<String>) = input
        .map { line -> line.split(" ").map { it.toInt() } }

    fun isSafe(report: List<Int>): Boolean {
        val distances = report.zipWithNext { a, b -> a - b }
        return (distances.all { abs(it) in 1..3 }  && distances.map { it.sign }.distinct().size == 1)
    }

    fun <T> subsetsExcludingOne(list: List<T>): List<List<T>> {
        return List(list.size) { index -> list.filterIndexed { i, _ -> i != index } }
    }

    fun part1(input: List<String>): Int = parseInput(input)
        .count { isSafe(it) }

    fun part2(input: List<String>): Int = parseInput(input)
        .count {
            (listOf(it) + subsetsExcludingOne(it)).any { report ->
                isSafe(report)
            }
        }

    val testInput = readInput("Day${day}_test")
    part1(testInput).checkEqualTo(2)
    part2(testInput).checkEqualTo(4)

    val input = readInput("Day${day}")
    part1(input).also { println() }.checkEqualTo(246)
    part2(input).also { println() }.checkEqualTo(318)
}
