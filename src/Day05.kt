fun main() {
    val day = "05"

    fun parseInput(input: List<String>): Pair<List<Pair<Int, Int>>, List<List<Int>>> = input
        .fold(mutableListOf(mutableListOf<String>())) { acc, item ->
            if (item.isEmpty()) acc.add(mutableListOf())
            else acc.last().add(item)
            acc
        }
        .let { (first, second) ->
            Pair(
                first.map { it.split("|").map { it.toInt() }.toPair() },
                second.map { it.split(",").map { it.toInt() } }
            )
        }

    fun part1(input: List<String>): Int {
        val (instructions, updates) = parseInput(input)
        return updates.filter { update ->
            instructions.all { (left, right) ->
                val leftIndex = update.indexOf(left)
                val rightIndex = update.indexOf(right)
                (leftIndex == -1) or (rightIndex == -1) or (leftIndex < rightIndex)
            }
        }.sumOf { it.elementAt((it.size - 1) / 2) }
    }

    fun part2(input: List<String>): Int {
        val (instructions, updates) = parseInput(input)
        return updates
            .mapNotNull { originalUpdate ->
                var changed = false
                var fixedUpdate: MutableList<Int> = originalUpdate.toMutableList()
                // multi-pass sort because dependencies
                var changedInPass: Boolean
                do {
                    changedInPass = false
                    fixedUpdate = instructions.fold(fixedUpdate) { update, (left, right) ->
                        val leftIndex = update.indexOf(left)
                        val rightIndex = update.indexOf(right)
                        if ((leftIndex != -1) and (rightIndex != -1) and (leftIndex > rightIndex)) {
                            update.swap(leftIndex, rightIndex)
                            changed = true
                            changedInPass = true
                        }
                        update
                    }
                } while (changedInPass)

                fixedUpdate.takeIf { changed }
            }
            .sumOf { it.elementAt((it.size - 1) / 2) }
    }

    val testInput = readInput("Day${day}_test")
    part1(testInput).checkEqualTo(143)
    part2(testInput).checkEqualTo(123)

    val input = readInput("Day${day}")
    part1(input).also { it.println() }.checkEqualTo(5732)
    part2(input).also { it.println() }.checkEqualTo(4716)
}