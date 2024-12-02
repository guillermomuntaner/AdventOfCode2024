fun main() {
    val day = "xx"

    fun parseInput(input: List<String>) = 1

    fun part1(input: List<String>): Int = parseInput(input)

    fun part2(input: List<String>): Int = parseInput(input)

    val testInput = readInput("Day${day}_test")
    //val testInput = listOf("ABC")
    part1(testInput).checkEqualTo(1)
    part2(testInput).checkEqualTo(1)

    val input = readInput("Day${day}")
    part1(input).also { println() }.checkEqualTo(1)
    part2(input).also { println() }.checkEqualTo(1)
}
