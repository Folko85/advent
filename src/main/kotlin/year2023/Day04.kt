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

    fun calculateScratch(input: String, resultMap: MutableList<Long>) {
        val inputArray: List<String> = input.split(":")
        val numbers: String = inputArray[1].trim()
        val values: List<String> = numbers.split("|")
        val winNumbers: List<String> = values[0].trim().split("\\s+".toRegex())
        val existNumbers: List<String> = values[1].trim().split("\\s+".toRegex())
        var counter: Long = 0
        for (i in existNumbers.indices) {
            if (winNumbers.contains(existNumbers[i])) {
                counter++
            }
        }
        resultMap.add(counter)
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
        val resultList: MutableList<Long> = mutableListOf()
        val numbersList: MutableList<Int> = mutableListOf()

        val strings: List<String> = File("src/main/resources/2023_day_4_input.txt").bufferedReader().readLines()
        strings.forEach {
            calculateScratch(it, resultList)
        }
        strings.forEach {
            numbersList.add(it.split(":")[0].trim().filter { str -> str.isDigit() }.toInt())
        }

        for (i in 1..resultList.size) {
            val count: Long = numbersList
                .stream().mapToInt { it.toInt() }
                .filter { it == i }.count()
            for (j in 1..count) {
                val prize = resultList[i - 1]
                for (k in i + 1..i + prize) {
                    numbersList.add(k.toInt())
                }
            }
        }

        numbersList.stream().filter { it < resultList.size }

        println(numbersList.size)
    }

    part1()
    part2()

}



