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
        var counter = 0L
        val intervals: MutableList<LongRange> = mutableListOf()

        val strings: List<String> = File("src/main/resources/year2025/day5_input.txt").bufferedReader().readLines()

        for (i in 0..<strings.size) {
            if (strings[i].isBlank()) {
                break
            } else {
                val intervalLong = strings[i].split("-".toRegex()).map { it.toLong() }
                val startInterval = intervalLong[0]
                val endInterval = intervalLong[1]
                intervals.add(LongRange(startInterval, endInterval))
            }
        }

        intervals.sortWith { o1, o2 -> o1.first.compareTo(o2.first) }

        var start = 0L
        var end = 0L

        var isNewInterval = true

        for (j in 1..<intervals.size) {
            val leftStart = intervals[j - 1].first
            val leftEnd = intervals[j - 1].last
            val rightStart = intervals[j].first
            val rightEnd = intervals[j].last
            if (isNewInterval) {
                start = leftStart
                end = leftEnd
            }

            if (rightStart <= end) {
                isNewInterval = false
                end = if (rightEnd > end) rightEnd else end
            } else {
                counter += (end - start + 1)
                isNewInterval = true
            }

        }
        counter += (end - start + 1)

        println(counter)
    }
    part1()
    part2()

}




