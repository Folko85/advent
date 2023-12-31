package year2023

import java.io.File

fun main() {


    fun checkAdjacent(j: Int, k: Int, array: Array<CharArray>): Boolean {
        var tempSymbol: Char
        var result = false
        if (j > 0 && k > 0) {
            tempSymbol = array[j - 1][k - 1]
            result = !tempSymbol.isDigit() && tempSymbol != '.'
        }
        if (j > 0 && k < array[0].size - 1 && !result) {
            tempSymbol = array[j - 1][k + 1]
            result = !tempSymbol.isDigit() && tempSymbol != '.'
        }
        if (j < array.size - 1 && k > 0 && !result) {
            tempSymbol = array[j + 1][k - 1]
            result = !tempSymbol.isDigit() && tempSymbol != '.'
        }
        if (j > 0 && !result) {
            tempSymbol = array[j - 1][k]
            result = !tempSymbol.isDigit() && tempSymbol != '.'
        }
        if (k > 0 && !result) {
            tempSymbol = array[j][k - 1]
            result = !tempSymbol.isDigit() && tempSymbol != '.'
        }
        if (j < array.size - 1 && k < array[0].size - 1 && !result) {
            tempSymbol = array[j + 1][k + 1]
            result = !tempSymbol.isDigit() && tempSymbol != '.'
        }
        if (j < array.size - 1 && !result) {
            tempSymbol = array[j + 1][k]
            result = !tempSymbol.isDigit() && tempSymbol != '.'
        }
        if (k < array[0].size - 1 && !result) {
            tempSymbol = array[j][k + 1]
            result = !tempSymbol.isDigit() && tempSymbol != '.'
        }
        return result
    }

    fun part1() {
        var sum: Long = 0
        val strings: List<String> = File("src/main/resources/2023_day_3_input.txt").bufferedReader().readLines()
        val rows: Int = strings.size
        val cols: Int = strings[0].length
        val array = Array(rows) { CharArray(cols) }
        for (i in 0..<rows) {
            array[i] = strings[i].toCharArray()
        }


        var accepted = false
        for (j in 0..<rows) {
            val number: StringBuilder = java.lang.StringBuilder()
            for (k in 0..<cols) {
                val symbol: Char = array[j][k]
                if (symbol.isDigit()) {
                    number.append(symbol)
                    if (!accepted) {
                        accepted = checkAdjacent(j, k, array)
                    }
                } else if (!symbol.isDigit()) {
                    if (number.isNotEmpty() && accepted) {
                        sum += number.toString().toLong()
                    }
                    number.clear()
                    accepted = false
                }

            }
            if (number.isNotEmpty() && accepted) {
                sum += number.toString().toLong()
            }
            number.clear()
            accepted = false

        }

        println(sum)
    }

    fun checkAdjacentGears(j: Int, k: Int, array: Array<CharArray>): String {
        var tempSymbol: Char
        if (j > 0 && k > 0) {
            tempSymbol = array[j - 1][k - 1]
            if (tempSymbol == '*') {
                return String.format("%d_%d", j - 1, k - 1)
            }
        }
        if (j > 0 && k < array[0].size - 1) {
            tempSymbol = array[j - 1][k + 1]
            if (tempSymbol == '*') {
                return String.format("%d_%d", j - 1, k + 1)
            }
        }
        if (j < array.size - 1 && k > 0) {
            tempSymbol = array[j + 1][k - 1]
            if (tempSymbol == '*') {
                return String.format("%d_%d", j + 1, k - 1)
            }
        }
        if (j > 0) {
            tempSymbol = array[j - 1][k]
            if (tempSymbol == '*') {
                return String.format("%d_%d", j - 1, k)
            }
        }
        if (k > 0) {
            tempSymbol = array[j][k - 1]
            if (tempSymbol == '*') {
                return String.format("%d_%d", j, k - 1)
            }
        }
        if (j < array.size - 1 && k < array[0].size - 1) {
            tempSymbol = array[j + 1][k + 1]
            if (tempSymbol == '*') {
                return String.format("%d_%d", j + 1, k + 1)
            }
        }
        if (j < array.size - 1) {
            tempSymbol = array[j + 1][k]
            if (tempSymbol == '*') {
                return String.format("%d_%d", j + 1, k)
            }
        }
        if (k < array[0].size - 1) {
            tempSymbol = array[j][k + 1]
            if (tempSymbol == '*') {
                return String.format("%d_%d", j, k + 1)
            }
        }
        return "*_*"
    }

    fun part2() {
        var sum: Long = 0
        val strings: List<String> = File("src/main/resources/2023_day_3_input.txt").bufferedReader().readLines()
        val rows: Int = strings.size
        val cols: Int = strings[0].length
        val array = Array(rows) { CharArray(cols) }
        for (i in 0..<rows) {
            array[i] = strings[i].toCharArray()
        }

        var accepted = false
        val gears: MutableMap<String, Long> = mutableMapOf()
        var gear = "*_*"
        for (j in 0..<rows) {
            val number: StringBuilder = java.lang.StringBuilder()
            for (k in 0..<cols) {
                val symbol: Char = array[j][k]
                if (symbol.isDigit()) {
                    number.append(symbol)
                    if (!accepted) {
                        gear = checkAdjacentGears(j, k, array)
                        if (gear != "*_*") {
                            accepted = true
                        }

                    }
                } else if (!symbol.isDigit()) {
                    if (number.isNotEmpty() && accepted) {
                        if (gears.containsKey(gear)) {
                            val first: Long = gears[gear]!!
                            sum += first * number.toString().toLong()
                            gears.remove(gear)
                        } else {
                            gears[gear] = number.toString().toLong()
                        }

                    }
                    gear = "*_*"
                    number.clear()
                    accepted = false
                }

            }
            if (number.isNotEmpty() && accepted) {
                if (gears.containsKey(gear)) {
                    val first: Long = gears[gear]!!
                    sum += first * number.toString().toLong()
                    gears.remove(gear)
                } else {
                    gears[gear] = number.toString().toLong()
                }
            }
            gear = "*_*"
            number.clear()
            accepted = false
        }

        println(sum)
    }

    part1()
    part2()


}



