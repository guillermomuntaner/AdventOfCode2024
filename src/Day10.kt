fun main() {
    val day = "10"

    fun parseInput(input: List<String>) = input
        .map { it.map { it.digitToInt() } }

    fun part1(input: List<String>): Int {
        val map = parseInput(input)

        val directions = listOf(
            Pair(-1, 0),
            Pair(0, 1),
            Pair(1, 0),
            Pair(0, -1)
        )

        val rows = map.size
        val cols = map[0].size

        fun search(pos: Pair<Int, Int>, value: Int): Set<Pair<Int, Int>> {
            if (value == 10) return setOf(pos)
            val results = mutableSetOf<Pair<Int, Int>>()
            for ((dx, dy) in directions) {
                val row = pos.first + dy
                val col = pos.second + dx
                if (row in 0 until rows && col in 0 until cols && map[row][col] == value) {
                    results.addAll(search(Pair(row, col), value = value + 1))
                }
            }
            return results
        }

        var score = 0
        for (y in 0 until rows) {
            for (x in 0 until cols) {
                if (map[y][x] == 0) {
                    score += search(Pair(y, x), value = 1).size
                }
            }
        }

        return score
    }

    fun part2(input: List<String>): Int {

        val map = parseInput(input)

        val directions = listOf(
            Pair(-1, 0),
            Pair(0, 1),
            Pair(1, 0),
            Pair(0, -1)
        )

        val rows = map.size
        val cols = map[0].size

        fun search(path: List<Pair<Int, Int>>): Set<List<Pair<Int, Int>>> {
            if (path.size == 10) return setOf(path)
            val results = mutableSetOf<List<Pair<Int, Int>>>()
            val tail = path.last()
            for ((dx, dy) in directions) {
                val row = tail.first + dy
                val col = tail.second + dx
                if (row in 0 until rows && col in 0 until cols && map[row][col] == path.size) {
                    results.addAll(search(path + listOf(Pair(row, col))))
                }
            }
            return results
        }

        var score = 0
        for (y in 0 until rows) {
            for (x in 0 until cols) {
                if (map[y][x] == 0) {
                    score += search(listOf(Pair(y, x))).size
                }
            }
        }

        return score
    }

    val testInput = readInput("Day${day}_test")
    part1(listOf("0123","1234","8765","9876")).checkEqualTo(1)
    part1(testInput).checkEqualTo(36)
    part2(testInput).checkEqualTo(81)

    val input = readInput("Day${day}")
    part1(input).also { it.println() }.checkEqualTo(794)
    part2(input).also { it.println() }.checkEqualTo(1706)
}
