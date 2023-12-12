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

    fun findPath(array: Array<Array<String>>): List<MutableList<Pair<Int, Int>>> {

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

        return paths

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

        val result: List<MutableList<Pair<Int, Int>>> = findPath(array)

        println(result[0].size - 1)
    }


    fun countPointsInLoop(listOpPoint: List<MutableList<Pair<Int, Int>>>, array: Array<Array<String>>): Int {

        val pathList: List<Pair<Int, Int>> = listOpPoint.flatMap { it.toList() }

        for (i in array.indices) {
            for (j in array[i].indices) {
                val point = Pair(i, j)
                if (!pathList.contains(point)) {
                    array[i][j] = "."
                }
            }
        }

        for (i in array.indices) {
            for (j in array[i].indices) {
                if (array[i][j] == "B") {
                    val rightPoint: Pair<Int, Int> = Pair(i, j + 1)
                    val leftPoint: Pair<Int, Int> = Pair(i, j - 1)
                    val upPoint: Pair<Int, Int> = Pair(i - 1, j)
                    val downPoint: Pair<Int, Int> = Pair(i + 1, j)
                    val west: Boolean = array[leftPoint.first][leftPoint.second] != "."
                    val ost: Boolean = array[rightPoint.first][rightPoint.second] != "."
                    val north: Boolean = array[upPoint.first][upPoint.second] != "."
                    val south: Boolean = array[downPoint.first][downPoint.second] != "."
                    if (west && ost) {
                        array[i][j] = "WO"
                    } else if (north && south) {
                        array[i][j] = "NS"
                    } else if (north && ost) {
                        array[i][j] = "NO"
                    } else if (south && ost) {
                        array[i][j] = "SO"
                    } else if (south && west) {
                        array[i][j] = "SW"
                    } else if (north && west) {
                        array[i][j] = "NW"
                    }
                }
            }
        }

        var result = 0
        for (i in array.indices) {
            var leftCounter = 0
            var rightCounter = 0
            for (j in array[i].indices) {
                val currentValue: String = array[i][j]
                if (currentValue != ".") {
                    if (currentValue.contains("N")) {
                        leftCounter++
                    }
                    if (currentValue.contains("S")) {
                        rightCounter++
                    }
                } else {
                    val counter = if (leftCounter < rightCounter) leftCounter else rightCounter
                    if (counter % 2 != 0) {
                        result++
                    }
                }
            }
        }

        return result
    }

    fun part2() {
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

        val contourArray: Array<Array<String>> = array.map { it.clone() }.toTypedArray()

        val path: List<MutableList<Pair<Int, Int>>> = findPath(array)
        val count: Int = countPointsInLoop(path, contourArray)

        println(count)
    }

    part1()
    part2()

}




