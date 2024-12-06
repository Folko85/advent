package year2024

import java.io.File

fun main() {

    fun sortAndCalculate(numbers: MutableList<Int>, edges: MutableMap<Int, MutableList<Int>>): Int {
        val localEdges: MutableMap<Int, MutableList<Int>> = mutableMapOf()
        val sortedNumbers: MutableList<Int> = mutableListOf()
        val assertNumbers: MutableList<Int> = mutableListOf<Int>().apply { addAll(numbers) }
        numbers.forEach { number ->
            val value: MutableList<Int> = edges[number] ?: mutableListOf()
            val filteredValue = value.filter { it in numbers }.toMutableList()
            localEdges[number] = filteredValue
        }
        while (numbers.isNotEmpty()) {
            for (number in numbers) {
                if (localEdges[number]!!.isEmpty()) {
                    sortedNumbers.add(number)
                    localEdges.remove(number)
                }
            }
            numbers.removeAll(sortedNumbers)
            localEdges.entries.forEach { it.value.removeAll(sortedNumbers) }
        }
        return if (sortedNumbers == assertNumbers) sortedNumbers[sortedNumbers.size / 2] else 0

    }

    fun part1() {
        val strings: List<String> = File("src/main/resources/year2024/day5_input.txt").bufferedReader().readLines()
        val edges: MutableMap<Int, MutableList<Int>> = mutableMapOf()
        val numbers: MutableList<MutableList<Int>> = mutableListOf()

        strings.forEach { string ->
            if (string.contains("|")) {
                val pair: List<Int> = string.split("|").map { it.trim().toInt() }.toList()
                edges.merge(pair[1], mutableListOf(pair[0])) { new, old -> (new + old).toMutableList() }
            } else if (string.contains(",")) {
                val unsortedNumbers: MutableList<Int> = string.split(",").map { it.trim().toInt() }.toMutableList()
                numbers.add(unsortedNumbers)
            }
        }

        var sum = 0
        numbers.forEach {
            sum += sortAndCalculate(it, edges)
        }
        println(sum)
    }
    part1()

}




