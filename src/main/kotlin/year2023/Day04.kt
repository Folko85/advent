package year2023

import java.io.File

fun main() {

    fun calculateLine(input: String): Long {
        val winMap: Array<Long> = arrayOf(0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384)
        val numbers: String = input.split(":")[1].trim()
        val values: List<String> = numbers.split("|")
        val winNumbers: List<String> = values[0].trim().split("\\s+".toRegex())
        val existNumbers: List<String> = values[1].trim().split("\\s+".toRegex())
        var counter = 0
        for (i in existNumbers.indices) {
            if (winNumbers.contains(existNumbers[i])) {
                counter++
            }
        }
        return winMap[counter]
    }

    fun part1() {
        var sum: Long = 0
        val strings: List<String> = File("src/main/resources/2023_day_4_input.txt").bufferedReader().readLines()
        strings.forEach {
            sum += calculateLine(it)
        }
        println(sum)
    }

    fun part2() {
        var sum: Long = 0
        val strings: List<String> = File("src/main/resources/2023_day_4_example.txt").bufferedReader().readLines()
        strings.forEach {
            sum += calculateLine(it)
        }
        println(sum)
    }

    part1()
//    part2()

}



