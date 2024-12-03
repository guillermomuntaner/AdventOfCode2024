fun main() {
    val day = "03"

    val regex1 = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")

    fun part1(input: List<String>): Int = input
            .flatMap { regex1.findAll(it) }
            .sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }

    val regex2 = Regex("""(mul\((\d{1,3}),(\d{1,3})\))|(do\(\))|(don't\(\))""")

    fun part2(input: List<String>): Int = input
    .flatMap { regex2.findAll(it) }
        .fold(Pair(0, true)) { acc, match ->
            var (sum, enabled) = acc
            when {
                enabled && match.groups[2] != null-> {
                    sum += match.groups[2]!!.value.toInt() * match.groups[3]!!.value.toInt()
                }
                match.groups[4] != null -> enabled = true
                match.groups[5] != null -> enabled = false
            }
            Pair(sum, enabled)
        }
        .first

    val testInput1 = readInput("Day${day}_test")
    //val testInput = listOf("ABC")
    part1(testInput1).also { it.println() }.checkEqualTo(161)
    val testInput2 = readInput("Day${day}_test2")
    part2(testInput2).also { it.println() }.checkEqualTo(48)

    val input = readInput("Day${day}")
    part1(input).also { it.println() }.checkEqualTo(189527826)
    part2(input).also { it.println() }.checkEqualTo(63013756)
}
