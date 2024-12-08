fun main() {
    val day = "08"

    fun part1(input: List<String>): Int {

        val rows = input.size
        val cols = input[0].length

        val antinodeLocations = mutableSetOf<Pair<Int, Int>>()

        for (y in 0 until rows) {
            for (x in 0 until cols) {

                val pos = input[y][x]
                if (pos == '.') continue

                for (ty in 0 until rows) {
                    for (tx in 0 until cols) {

                        if (ty == y && tx == x) continue

                        val tpos = input[ty][tx]
                        if (tpos == '.') continue
                        if (tpos == pos) {
                            val dx = tx - x
                            val dy = ty - y

                            val x1 = tx + dx
                            val y1 = ty + dy
                            if (y1 in 0 until rows && x1 in 0 until cols) {
                                antinodeLocations.add(Pair(y1, x1))
                            }

                            val x2 = x - dx
                            val y2 = y - dy
                            if (y2 in 0 until rows && x2 in 0 until cols) {
                                antinodeLocations.add(Pair(y2, x2))
                            }
                        }

                    }
                }

            }
        }

        return antinodeLocations.size
    }

    fun part2(input: List<String>): Int {

        val rows = input.size
        val cols = input[0].length

        val antinodeLocations = mutableSetOf<Pair<Int, Int>>()

        for (y in 0 until rows) {
            for (x in 0 until cols) {

                val pos = input[y][x]
                if (pos == '.') continue

                for (ty in 0 until rows) {
                    for (tx in 0 until cols) {

                        if (ty == y && tx == x) continue

                        val tpos = input[ty][tx]
                        if (tpos == '.') continue
                        if (tpos == pos) {
                            val dx = tx - x
                            val dy = ty - y
                            val div = gcd(dx, dy)
                            val mindx = dx / div
                            val mindy = dy / div

                            antinodeLocations.add(Pair(y, x))

                            var mul = 1
                            while (true) {
                                val x1 = x + mul * mindx
                                val y1 = y + mul * mindy
                                mul += 1
                                if (y1 in 0 until rows && x1 in 0 until cols) {
                                    antinodeLocations.add(Pair(y1, x1))
                                } else {
                                    break
                                }
                            }

                            mul = -1
                            while (true) {
                                val x1 = x + mul * mindx
                                val y1 = y + mul * mindy
                                mul -= 1
                                if (y1 in 0 until rows && x1 in 0 until cols) {
                                    antinodeLocations.add(Pair(y1, x1))
                                } else {
                                    break
                                }
                            }
                        }
                    }
                }

            }
        }

        return antinodeLocations.size
    }

    val testInput = readInput("Day${day}_test")
    part1(testInput).checkEqualTo(14)
    part2(testInput).checkEqualTo(34)

    val input = readInput("Day${day}")
    part1(input).also { it.println() }.checkEqualTo(265)
    part2(input).also { it.println() }.checkEqualTo(962)
}
