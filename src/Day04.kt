fun main() {
    val day = "04"

    fun part1(input: List<String>): Int {

        val word = "XMAS"

        val directions = listOf(
            Pair(-1, 0),
            Pair(-1, 1),
            Pair(0, 1),
            Pair(1, 1),
            Pair(1, 0),
            Pair(1, -1),
            Pair(0, -1),
            Pair(-1, -1)
        )

        val rows = input.size
        val cols = input[0].length
        var count = 0

        for (y in 0 until rows) {
            for (x in 0 until cols) {
                directions@ for ((dx, dy) in directions) {
                    for (i in word.indices) {
                        val row = y + i * dy
                        val col = x + i * dx
                        if (row !in 0 until rows || col !in 0 until cols || input[row][col] != word[i]) {
                            continue@directions
                        }
                    }
                    count++
                }
            }
        }

        return count
    }

    fun part2(input: List<String>): Int  {

        val words = listOf("MAS", "MAS".reversed())

        val directions = listOf(
            Pair(-1, 1),
            Pair(1, 1),
        )

        val rows = input.size
        val cols = input[0].length
        var count = 0

        //(0 until rows).sumOf { y ->
        //    (0 until cols).count { x ->
        //        directions.all { direction ->
        //            words.any { word ->
//
        //            }
        //        }
        //    }
        //}

        for (y in 0 until rows) {
            position@ for (x in 0 until cols) {
                directions@ for ((dx, dy) in directions) {
                    words@ for (word in words) {
                        for (i in -1..1) {
                            val row = y + i * dy
                            val col = x + i * dx
                            if (row !in 0 until rows || col !in 0 until cols || input[row][col] != word[i + 1]) {
                                // Miss-match, try other variation
                                continue@words
                            }
                        }
                        // Match, move to next direction
                        continue@directions
                    }
                    // No matching word in the direction, move to next position
                    continue@position
                }
                count++
            }
        }

        return count
    }

    val testInput = readInput("Day${day}_test")
    part1(testInput).checkEqualTo(18)
    part2(testInput).checkEqualTo(9)

    val input = readInput("Day${day}")
    part1(input).also { it.println() }.checkEqualTo(2613)
    part2(input).also { it.println() }.checkEqualTo(1905)
}
