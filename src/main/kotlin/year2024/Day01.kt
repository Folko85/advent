package year2024

import java.io.File
import java.math.BigInteger
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

    fun part2() {
        val strings: List<String> = File("src/main/resources/year2024/day1_input.txt").bufferedReader().readLines()
        val firstList: MutableList<BigInteger> = mutableListOf()
        val secondMap: MutableMap<BigInteger, BigInteger> = mutableMapOf()
        strings.forEach {
            run {
                val numbers: List<String> = it.split("\\s+".toRegex())
                firstList.add(numbers[0].trim().toBigInteger())
                secondMap.merge(numbers[1].trim().toBigInteger(), BigInteger.ONE) { new, old -> new + old }
            }
        }

        var sum = BigInteger.ZERO
        firstList.forEach {
            val elementCount: BigInteger = secondMap[it] ?: BigInteger.ZERO
            sum = sum.add(it.multiply(elementCount))
        }
        println(sum)
    }


    part1()
    part2()
}