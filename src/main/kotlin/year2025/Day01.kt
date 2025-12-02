package year2025

import java.io.File

fun main() {

    fun part1() {
        var resultCounter = 0
        val maxValue = 99
        val minValue = 0
        var currentValue = 50
        val roundSize = 100
        val commands: List<String> = File("src/main/resources/year2025/day1_input.txt").bufferedReader().readLines()
        commands.forEach {
            val direction = it[0]
            val moveValue = it.substring(1).toInt()
            val clearedMoveValue = moveValue % roundSize
            if (direction == 'R') {
                val toEdgeValue = maxValue - currentValue
                if (clearedMoveValue <= toEdgeValue) {
                    currentValue += clearedMoveValue
                } else {
                    currentValue -= (roundSize - clearedMoveValue)
                }

            } else if (direction == 'L') {
                val toEdgeValue = currentValue - minValue
                if (clearedMoveValue <= toEdgeValue) {
                    currentValue -= clearedMoveValue
                } else {
                    currentValue += (roundSize - clearedMoveValue)
                }
            }
            if (currentValue == 0) {
                resultCounter++
            }
        }
        println(resultCounter)

    }

    fun part2() {
        var resultCounter = 0
        val maxValue = 99
        val minValue = 0
        var currentValue = 50
        val roundSize = 100
        val commands: List<String> = File("src/main/resources/year2025/day1_input.txt").bufferedReader().readLines()
        commands.forEach {
            val direction = it[0]
            val moveValue = it.substring(1).toInt()
            val roundCount = moveValue / roundSize
            resultCounter += roundCount
            val clearedMoveValue = moveValue % roundSize
            if (direction == 'R') {
                val toEdgeValue = maxValue - currentValue
                if (clearedMoveValue <= toEdgeValue) {
                    currentValue += clearedMoveValue
                } else {
                    currentValue -= (roundSize - clearedMoveValue)
                    if (currentValue != 0){
                        resultCounter++
                    }

                }

            } else if (direction == 'L') {
                val toEdgeValue = currentValue - minValue
                if (clearedMoveValue <= toEdgeValue) {
                    currentValue -= clearedMoveValue
                } else {
                    val previousValue = currentValue
                    currentValue += (roundSize - clearedMoveValue)
                    if (currentValue != 0 && previousValue != 0){
                        resultCounter++
                    }


                }
            }
            if (currentValue == 0){
                resultCounter++
            }
        }
        println(resultCounter)

    }

    part1()
    part2()
}