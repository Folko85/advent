package year2024

import java.io.File
import kotlin.math.abs

fun main() {

    fun part1() {
        val strings: List<String> = File("src/main/resources/year2024/day2_input.txt").bufferedReader().readLines()
        var sum = 0
        strings.forEach {
            if (checkString(it)) {
                sum++;
            }
        }
        println(sum)
    }

    part1()

}

fun checkString(string: String): Boolean {
    val numbers: List<Int> = string.split("\\s+".toRegex()).map { it.trim().toInt() }
    var increase = false
    var decrease = false
    for (i in numbers.indices) {
        if (i == 0) {
            continue
        }
        if (numbers[i] > numbers[i - 1]) {
            increase = true
        }
        if (numbers[i] < numbers[i - 1]) {
            decrease = true
        }
        if (increase && decrease) {
            return false
        }
        val diff = abs(numbers[i] - numbers[i - 1])
        if (diff < 1 || diff > 3) {
            return false
        }
    }
    return true

}