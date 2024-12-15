package year2024

import java.io.File

fun main() {

    val cache = mutableMapOf<Pair<Long, Int>, Long>()

    fun blink(stone: Long): List<Long> = when {
        stone == 0L -> listOf(1L)
        stone.toString().length % 2 == 0 -> {
            val whole = stone.toString()
            listOf(whole.take(whole.length / 2).toLong(), whole.drop(whole.length / 2).toLong())
        }

        else -> listOf(stone * 2024)
    }

    fun calculateCount(stone: Long, iterations: Int): Long {
        if (iterations == 0) return 1
        cache[stone to iterations]?.let { return it }
        return blink(stone).sumOf { calculateCount(it, iterations - 1) }
            .also { cache[stone to iterations] = it }
    }

    fun solve(iterations: Int) {
        val string: String = File("src/main/resources/year2024/day11_input.txt").bufferedReader().readLine()!!
        val sourceNumbers: MutableList<Long> = string.split("\\s+".toRegex()).map { it.trim().toLong() }.toMutableList()

        val sum = sourceNumbers.sumOf { calculateCount(it, iterations) }
        println(sum)

    }

    fun part1() {
        solve(25)
    }

    fun part2() {
        solve(75)
    }

    part1()
    part2()
}