package year2025

import java.io.File

fun main() {

    fun part1() {
        var result = 0
        val batteries: List<String> = File("src/main/resources/year2025/day3_input.txt").bufferedReader().readLines()
        batteries.forEach { battery ->
            var firstDigit = 0
            var firstPosition = 0
            for (i in 0..<battery.length - 1) {
                val currentDigit = battery[i].toString().toInt()
                if (currentDigit > firstDigit) {
                    firstDigit = currentDigit
                    firstPosition = i
                }
            }
            var secondDigit = 0
            for (j in firstPosition + 1..<battery.length) {
                val currentDigit = battery[j].toString().toInt()
                secondDigit = if (currentDigit > secondDigit) currentDigit else secondDigit
            }
            result += firstDigit * 10 + secondDigit
        }
        println(result)
    }

    fun part2() {

    }

    part1()
    part2()
}