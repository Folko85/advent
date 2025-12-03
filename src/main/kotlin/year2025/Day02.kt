package year2025

import java.io.File

fun main() {

    fun part1() {
        var result = 0L
        val input: String = File("src/main/resources/year2025/day2_input.txt").bufferedReader().readLine()
        val intervalStrings: List<String> = input.split(",".toRegex())
        val ranges: MutableList<LongRange> = mutableListOf()
        intervalStrings.forEach { interval ->
            val intervalLong: List<Long> = interval.split("-".toRegex()).map {
                it.toLong()
            }
            ranges.add(LongRange(intervalLong[0], intervalLong[1]))
        }
        ranges.forEach { range ->
            range.forEach { value ->
                var mirrorFlag: Boolean
                val valueString = value.toString()
                val stringSize = valueString.length
                if (stringSize % 2 == 0) {
                    mirrorFlag = true
                    for (i in 0..<stringSize / 2) {
                        if (valueString[i] != valueString[stringSize / 2 + i]) {
                            mirrorFlag = false
                            break
                        }
                    }
                    if (mirrorFlag) {
                        println(value)
                        result += value
                    }
                }
            }
        }
        println(result)
    }

    fun part2() {

    }

    part1()
    part2()
}