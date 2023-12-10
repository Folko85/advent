package year2023

import java.io.File

fun main() {


    fun calculate(input: String): Long {
        val list: MutableList<Long> = input.split("\\s+".toRegex()).map {
            it.trim().toLong()
        }.toMutableList()
        val matrix: MutableList<MutableList<Long>> = mutableListOf()
        matrix.add(list)
        while (true) {
            val currentList = matrix[matrix.size - 1]
            if (currentList.all { it == currentList[0] }) {
                currentList.add(currentList[0])
                break
            }
            val nextList: MutableList<Long> = mutableListOf()
            for (i in 0..currentList.size - 2) {
                nextList.add(currentList[i + 1] - currentList[i])
            }
            matrix.add(nextList)
        }

        for (j in matrix.size - 1 downTo 1) {
            val lowerList = matrix[j]
            val topList = matrix[j - 1]
            topList.add(topList[topList.size - 1] + lowerList[lowerList.size - 1])
        }
        return list[list.size - 1]
    }

    fun part1() {
        var sum = 0L
        val strings: List<String> = File("src/main/resources/2023_day_9_input.txt").bufferedReader().readLines()
        strings.forEach {
            val nextResult: Long = calculate(it)
            sum += nextResult
        }

        println(sum)
    }

    fun calculateInvert(input: String): Long {
        val list: MutableList<Long> = input.split("\\s+".toRegex()).map {
            it.trim().toLong()
        }.toMutableList()
        val matrix: MutableList<MutableList<Long>> = mutableListOf()
        matrix.add(list)
        while (true) {
            val currentList = matrix[matrix.size - 1]
            if (currentList.all { it == currentList[0] }) {
                currentList.add(currentList[0])
                break
            }
            val nextList: MutableList<Long> = mutableListOf()
            for (i in 0..currentList.size - 2) {
                nextList.add(currentList[i + 1] - currentList[i])
            }
            matrix.add(nextList)
        }

        for (j in matrix.size - 1 downTo 1) {
            val lowerList = matrix[j]
            val topList = matrix[j - 1]
            val value = topList[0] - lowerList[0]
            topList.add(0, value)
        }
        return list[0]
    }

    fun part2() {
        var sum = 0L
        val strings: List<String> = File("src/main/resources/2023_day_9_input.txt").bufferedReader().readLines()
        strings.forEach {
            val nextResult: Long = calculateInvert(it)
            sum += nextResult
        }

        println(sum)

    }

    part1()
    part2()

}




