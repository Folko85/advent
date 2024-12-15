package year2024

import utils.Utils
import java.io.File

fun main() {

    fun part1() {
        val string: String = File("src/main/resources/year2024/day11_input.txt").bufferedReader().readLine()!!

        val numbers: MutableList<Long> = string.split("\\s+".toRegex()).map { it.trim().toLong() }.toMutableList()
        val newNumbers: MutableList<Long> = mutableListOf()

        for (i in 1..25) {
            for (j in numbers.indices) {
                if (numbers[j] == 0L) {
                    newNumbers.add(1L)
                } else if (numbers[j].toString().length % 2 == 0) {
                    val newLength = numbers[j].toString().length / 2
                    val divider = Utils.power(10, newLength)
                    newNumbers.add(numbers[j] / divider)
                    newNumbers.add(numbers[j] % divider)

                } else {
                    newNumbers.add(numbers[j] * 2024)
                }
            }
            numbers.clear()
            numbers.addAll(newNumbers)
            newNumbers.clear()
        }

        println(numbers.size)
    }


    part1()
}