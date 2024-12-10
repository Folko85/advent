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

    part1()
}