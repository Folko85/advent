package year2024

import java.io.File

fun main() {

    fun findAntiNodes(nodes: MutableList<Pair<Int, Int>>): MutableSet<Pair<Int, Int>> {
        val result: MutableSet<Pair<Int, Int>> = mutableSetOf()
        for (i in nodes.indices) {
            val firstPair = nodes[i]
            for (j in i + 1..<nodes.size) {
                val secondPair = nodes[j]
                result.add(Pair(firstPair.first * 2 - secondPair.first, firstPair.second * 2 - secondPair.second))
                result.add(Pair(secondPair.first * 2 - firstPair.first, secondPair.second * 2 - firstPair.second))
            }
        }
        return result
    }

    fun part1() {
        val nodes: MutableMap<String, MutableList<Pair<Int, Int>>> = mutableMapOf()
        val strings: List<String> = File("src/main/resources/year2024/day8_input.txt").bufferedReader().readLines()
        val rows: Int = strings.size
        val cols: Int = strings[0].length
        val array: Array<Array<String>> = Array(rows) { Array(cols) { "" } }
        for (i in 0..<rows) {
            val row: String = strings[i]
            for (j in 0..<cols) {
                val symbol: String = row[j].toString()
                array[i][j] = symbol
                if (symbol != ".") {
                    nodes.merge(symbol, mutableListOf(Pair(i, j))) { new, old -> (new + old).toMutableList() }
                }
            }
        }

        val antiNodes: MutableSet<Pair<Int, Int>> = mutableSetOf()

        nodes.values.forEach {
            antiNodes.addAll(findAntiNodes(it))
        }

        val sum = antiNodes.count { it.first >= 0 && it.first < array.size && it.second >= 0 && it.second < array[0].size }
        println(sum)
    }



    part1()

}




