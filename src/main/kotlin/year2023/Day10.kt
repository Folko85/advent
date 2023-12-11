package year2023

import java.io.File

fun main() {

    fun interpret(input: String): String {
        val result = when (input) {
            "|" -> "SN"
            "-" -> "WO"
            "L" -> "NO"
            "F" -> "SO"
            "7" -> "SW"
            "J" -> "NW"
            "S" -> "B"
            else -> input
        }
        return result
    }

    fun findPath(array: Array<Array<String>>): Int {

        val firstPath: MutableList<Pair<Int, Int>> = mutableListOf()
        val secondPath: MutableList<Pair<Int, Int>> = mutableListOf()
        val paths: List<MutableList<Pair<Int, Int>>> = listOf(firstPath, secondPath)

        for (i in array.indices) {
            for (j in array[i].indices) {
                if (array[i][j] == "B") {
                    val beginPoint: Pair<Int, Int> = Pair(i, j)
                    firstPath.add(beginPoint)
                    secondPath.add(beginPoint)
                    var currentPath = firstPath
                    val rightPoint: Pair<Int, Int> = Pair(i, j + 1)
                    val leftPoint: Pair<Int, Int> = Pair(i, j - 1)
                    val upPoint: Pair<Int, Int> = Pair(i - 1, j)
                    val downPoint: Pair<Int, Int> = Pair(i + 1, j)
                    val upString: String = array[upPoint.first][upPoint.second]
                    if (upString.contains("S")) {
                        currentPath.add(upPoint)
                        array[upPoint.first][upPoint.second] = upString.replace("S", "")
                        currentPath = secondPath
                    }
                    val downString = array[downPoint.first][downPoint.second]
                    if (downString.contains("N")) {
                        currentPath.add(downPoint)
                        array[downPoint.first][downPoint.second] = downString.replace("N", "")
                        currentPath = secondPath
                    }
                    val leftString = array[leftPoint.first][leftPoint.second]
                    if (leftString.contains("O")) {
                        currentPath.add(leftPoint)
                        array[leftPoint.first][leftPoint.second] = leftString.replace("O", "")
                        currentPath = secondPath
                    }
                    val rightString = array[rightPoint.first][rightPoint.second]
                    if (rightString.contains("W")) {
                        currentPath.add(rightPoint)
                        array[rightPoint.first][rightPoint.second] = rightString.replace("W", "")
                    }
                }
            }
        }

        while (firstPath[firstPath.size - 1] != secondPath[secondPath.size - 1]) {
            for (i in paths.indices) {
                val list = paths[i]
                val lastPoint = list[list.size - 1]
                val pipeString = array[lastPoint.first][lastPoint.second]
                when (pipeString) {
                    "S" -> {
                        val nextPoint = Pair(lastPoint.first + 1, lastPoint.second)
                        val value = array[nextPoint.first][nextPoint.second]
                        array[nextPoint.first][nextPoint.second] = value.replace("N", "")
                        list.add(nextPoint)
                    }

                    "N" -> {
                        val nextPoint = Pair(lastPoint.first - 1, lastPoint.second)
                        val value = array[nextPoint.first][nextPoint.second]
                        array[nextPoint.first][nextPoint.second] = value.replace("S", "")
                        list.add(nextPoint)
                    }

                    "O" -> {
                        val nextPoint = Pair(lastPoint.first, lastPoint.second + 1)
                        val value = array[nextPoint.first][nextPoint.second]
                        array[nextPoint.first][nextPoint.second] = value.replace("W", "")
                        list.add(nextPoint)
                    }

                    "W" -> {
                        val nextPoint = Pair(lastPoint.first, lastPoint.second - 1)
                        val value = array[nextPoint.first][nextPoint.second]
                        array[nextPoint.first][nextPoint.second] = value.replace("O", "")
                        list.add(nextPoint)
                    }
                }
            }
        }


        return firstPath.size - 1

    }

    fun part1() {
        val strings: List<String> = File("src/main/resources/2023_day_10_input.txt").bufferedReader().readLines()
        val rows: Int = strings.size
        val cols: Int = strings[0].length
        val array: Array<Array<String>> = Array(rows) { Array(cols) { "" } }
        for (i in 0..<rows) {
            val row: String = strings[i]
            for (j in 0..<cols) {
                array[i][j] = interpret(row[j].toString())
            }
        }

        val result: Int = findPath(array)

        println(result)
    }


    fun part2() {
        var sum = 0L


    }

    part1()
//    part2()

}




