package year2024

import java.io.File
import kotlin.math.abs

fun main() {

    fun part1() {
        val strings: List<String> = File("src/main/resources/year2024/day1_input.txt").bufferedReader().readLines()
        val firstList: MutableList<Long> = mutableListOf()
        val secondList: MutableList<Long> = mutableListOf()
        strings.forEach {
            run {
                val numbers: List<String> = it.split("\\s+".toRegex())
                firstList.add(numbers[0].trim().toLong())
                secondList.add(numbers[1].trim().toLong())
            }
        }
        val sortedFirst: List<Long> = firstList.sorted()
        val sortedSecond: List<Long> = secondList.sorted()
        var sum = 0L
        for (i in sortedFirst.indices) {
            sum += abs(sortedFirst[i] - sortedSecond[i])
        }
        println(sum)

    }


    part1()
}