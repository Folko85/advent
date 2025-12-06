package year2025

import java.io.File

fun main() {

    fun checkFreshId(id: String, ranges: MutableList<LongRange>): Boolean {
        ranges.forEach { range ->
            if (id.toLong() in range) {
                return true
            }
        }
        return false
    }

    fun part1() {
        val intervals: MutableList<LongRange> = mutableListOf()
        var counter = 0
        val strings: List<String> = File("src/main/resources/year2025/day5_input.txt").bufferedReader().readLines()
        var trigger = false
        strings.forEach { str ->
            if (trigger && checkFreshId(str, intervals)) {
                counter++
            } else if (str.isBlank()) {
                trigger = true
            } else if (!trigger) {
                val intervalLong = str.split("-".toRegex()).map { it.toLong() }
                intervals.add(LongRange(intervalLong[0], intervalLong[1]))
            }
        }
        println(counter)

    }

    fun part2() {

    }
    part1()
    part2()

}




