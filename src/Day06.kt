fun main() {
    val day = "06"

    fun part1(input: List<String>): Int {

        val directions = listOf<Pair<Char, Pair<Int, Int>>>(
            '^' to Pair(-1, 0),
            '>' to Pair( 0, 1),
            'v' to Pair( 1, 0),
            '<' to Pair( 0,-1)
        )

        val rows = input.size
        val cols = input[0].length

        var (col, row, dirIndex) = input
            .flatMapIndexed { row, line ->
                line.mapIndexedNotNull { col, char ->
                    val dirIndex = directions.indexOfFirst { it.first == char }
                    if (dirIndex != -1) Triple(col, row, dirIndex) else null
                }
            }
            .first()

        val visitedPositions: MutableSet<Pair<Int, Int>> = mutableSetOf()

        while (true) {
            visitedPositions.add(Pair(row, col))
            val (dRow, dCol) = directions[dirIndex].second
            val newRow = row + dRow
            val newCol = col + dCol
            if (newRow !in 0 until rows || newCol !in 0 until cols) {
                break
            } else if (input[newRow][newCol] == '#') {
                dirIndex = (dirIndex + 1) % directions.size
            } else {
                row = newRow
                col = newCol
            }
        }

        return visitedPositions.size
    }

    fun part2(input: List<String>): Int {

        val directions = listOf<Pair<Char, Pair<Int, Int>>>(
            '^' to Pair(-1, 0),
            '>' to Pair( 0, 1),
            'v' to Pair( 1, 0),
            '<' to Pair( 0,-1)
        )

        val rows = input.size
        val cols = input[0].length

        val (col, row, dirIndex) = input
            .flatMapIndexed { row, line ->
                line.mapIndexedNotNull { col, char ->
                    val dirIndex = directions.indexOfFirst { it.first == char }
                    if (dirIndex != -1) Triple(col, row, dirIndex) else null
                }
            }
            .first()

        var loops = 0

        for (y in 0 until rows) {
            for (x in 0 until cols) {

                if (row == y && col == x) continue

                var row = row
                var col = col
                var dirIndex = dirIndex

                val visitedPositions: MutableSet<Triple<Int, Int, Int>> = mutableSetOf()

                while (true) {
                    if (!visitedPositions.add(Triple(row, col, dirIndex))) {
                        loops += 1
                        break
                    }

                    val (dRow, dCol) = directions[dirIndex].second
                    val newRow = row + dRow
                    val newCol = col + dCol
                    if (newRow !in 0 until rows || newCol !in 0 until cols) {
                        break
                    } else if ((newRow == y && newCol == x) || input[newRow][newCol] == '#') {
                        dirIndex = (dirIndex + 1) % directions.size
                    } else {
                        row = newRow
                        col = newCol
                    }
                }
            }
        }


        return loops
    }

    val testInput = readInput("Day${day}_test")

    part1(testInput).checkEqualTo(41)
    part2(testInput).checkEqualTo(6)

    val input = readInput("Day${day}")
    part1(input).also { it.println() }.checkEqualTo(4454)
    part2(input).also { it.println() }.checkEqualTo(1)
}
