package year2024

import java.io.File

fun main() {

    fun part1() {
        val string: String = File("src/main/resources/year2024/day9_input.txt").bufferedReader().readLine()!!
        val parsed: List<Int?> =
            string.flatMapIndexed { index, c -> List(c.digitToInt()) { if (index % 2 == 0) index / 2 else null } }
        var start = 0
        var end = parsed.size - 1
        val filtered: MutableList<Int> = mutableListOf()
        while (start <= end) {
            if (parsed[start] != null) {
                filtered.add(parsed[start]!!)
                start++
            } else {
                while (parsed[end] == null) {
                    end--
                }
                filtered.add(parsed[end]!!)
                end--
                start++
            }
        }
        var sum = 0L
        for (i in filtered.indices) {
            sum += i.toLong() * filtered[i].toLong()
        }
        println(sum)
    }

    fun findIndex(spaceCount: Int, spacesMap: MutableMap<Int, Int>): Int {
        val sortedMap = spacesMap.toSortedMap()
        for (entry in sortedMap.entries) {
            if (entry.value >= spaceCount) {
                return entry.key
            }
        }
        return 0
    }

    fun part2() {
        val string: String = File("src/main/resources/year2024/day9_input.txt").bufferedReader().readLine()!!

        val all: MutableList<Int> = string.map { it.digitToInt() }.toMutableList()
        val spacesMap: MutableMap<Int, Int> = mutableMapOf()
        var key = 0
        for (a in all.indices) {
            if (a % 2 != 0) {
                spacesMap[key] = all[a]
            }
            key += all[a]

        }

        val parsed: MutableList<Int?> =
            string.flatMapIndexed { index, c -> List(c.digitToInt()) { if (index % 2 == 0) index / 2 else null } }.toMutableList()

        for (i in all.size - 1 downTo 0 step 2) {
            val length = all[i]
            for (j in 1..<i step 2) {
                val spaceCount = all[j]
                if (spaceCount >= length) {
                    for (index in parsed.indices) {
                        if (parsed[index] == i / 2) {
                            parsed[index] = null
                        }
                    }
                    val parsedIndex: Int = findIndex(spaceCount, spacesMap)
                    for (l in 0..<length) {
                        parsed[parsedIndex + l] = i / 2
                    }
                    all[j] = spaceCount - length
                    spacesMap.remove(parsedIndex)
                    if (spaceCount - length > 0) {
                        spacesMap[parsedIndex + length] = spaceCount - length
                    }
                    break
                }
            }
        }

        val filtered = parsed.map { it ?: 0 }

        var sum = 0L
        for (i in filtered.indices) {
            sum += i.toLong() * filtered[i].toLong()
        }
        println(sum)
    }

    part1()
    part2()
}