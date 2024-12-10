
sealed class FSItem {
    class File(val id: Int, val size: Int): FSItem()
    class Space(val size: Int): FSItem()
}

fun main() {
    val day = "09"

    fun part1(input: List<String>): Long {
        val disk = input
            .first()
            .map { it.digitToInt() }
            .flatMapIndexed { index, count ->
                List(count) { if (index % 2 == 0) index / 2 else null }
            }
            .toMutableList()

        var i = 0
        var f = disk.lastIndex
        while(i < f) {
            if (disk[i] != null) {
                i += 1
                continue
            }
            if (disk[f] == null) {
                f -= 1
                continue
            }
            disk[i] = disk[f]
            disk[f] = null
        }

        return disk.mapIndexed { index, id ->
            index.toLong() * (id?.toLong() ?: 0)
        }.sum()
    }

    fun part2(input: List<String>): Long {
        val disk: MutableList<FSItem> = input
            .first()
            .map { it.digitToInt() }
            .mapIndexed { index, count ->
                if (index % 2 == 0) {
                    FSItem.File(id = index / 2, size = count)
                } else {
                    FSItem.Space(size = count)
                }
            }
            .toMutableList()

        var i = 0
        var f = disk.lastIndex
        while(f > 0) {
            if (f <= i) {
                i = 0
                f -= 1
                continue
            }
            val targetFile: FSItem.File
            when (val item = disk[f]) {
                is FSItem.File -> targetFile = item
                is FSItem.Space -> {
                    f -= 1
                    continue
                }
            }
            when (val item = disk[i]) {
                is FSItem.File -> {
                    i += 1
                    continue
                }
                is FSItem.Space -> {
                    if (targetFile.size < item.size) {
                        val tmp = disk[f]
                        disk[i] = FSItem.Space(size = item.size - targetFile.size)
                        disk[f] = FSItem.Space(size = targetFile.size)
                        disk.add(i, tmp)
                        f -= 1
                        i = 0
                    } else if (targetFile.size == item.size) {
                        val tmp = disk[i]
                        disk[i] = disk[f]
                        disk[f] = tmp
                        f -= 1
                        i = 0
                    } else {
                        i += 1
                    }
                }
            }
        }

        val flattenDisk = disk.flatMap { item ->
            when (item) {
                is FSItem.File -> List(item.size) { item.id }
                is FSItem.Space -> List(item.size) { null }
            }
        }

        return flattenDisk.mapIndexed { index, id ->
            index.toLong() * (id?.toLong() ?: 0)
        }.sum()
    }

    val testInput = readInput("Day${day}_test")
    part1(testInput).checkEqualTo(1928L)
    part2(testInput).checkEqualTo(2858L)

    val input = readInput("Day${day}")
    part1(input).also { it.println() }.checkEqualTo(6366665108136L)
    part2(input).also { it.println() }.checkEqualTo(6398065450842L)
}
