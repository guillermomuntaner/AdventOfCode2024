
fun main() {
    val day = "07"

    fun parseInput(input: List<String>): List<Pair<Long, List<Long>>> = input
        .map {
            it.split(": ")
                .let {
                    Pair(
                        it[0].toLong(),
                        it[1].split(" ").map { it.toLong() }
                    )
                }
        }

    fun calculateCalibration(
        input: List<String>,
        operators: List<(Long, Long) -> Long>
    ) = parseInput(input)
        .filter { (testValue, numbers) ->
            numbers.fold(emptyList<Long>()) { acc, next ->
                if (acc.isEmpty()) {
                    listOf(next)
                } else {
                    acc.flatMap { number ->
                        operators.map { it(number, next) }
                    }
                }
            }.any { it == testValue }
        }
        .sumOf { it.first }

    fun part1(input: List<String>): Long = calculateCalibration(input, listOf(Long::plus, Long::times))

    fun part2(input: List<String>): Long = calculateCalibration(
        input,
        listOf(
            Long::plus,
            Long::times,
            { a, b -> (a.toString() + b.toString()).toLong() }
        )
    )

    val testInput = readInput("Day${day}_test")
    //val testInput = listOf("ABC")
    part1(testInput).checkEqualTo(3749L)
    part2(testInput).checkEqualTo(11387L)

    val input = readInput("Day${day}")
    part1(input).also { it.println() }.checkEqualTo(5702958180383L)
    part2(input).also { it.println() }.checkEqualTo(92612386119138L)
}
