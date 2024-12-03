package year2024

import java.io.File
import kotlin.math.abs

fun main() {
    part1()
    part2()

}

fun part1() {
    val input: List<String> = File("src/main/resources/year2024/day2_input.txt").bufferedReader().readLines()
    println(input.map { it.split(" ").map(String::toInt) }.count { isSafe(it) })
}

fun part2() {
    val input: List<String> = File("src/main/resources/year2024/day2_input.txt").bufferedReader().readLines()
    println(input.map { it.split(" ").map(String::toInt) }.count { isSafeWithDampener(it) })
}

fun isSafe(report: List<Int>): Boolean {
    val isIncreasing = report.zipWithNext().all { it.second > it.first }
    val isDecreasing = report.zipWithNext().all { it.second < it.first }
    val differencesOk = report.zipWithNext().all { (a, b) -> (1..3).contains(abs(b - a)) }
    return (isIncreasing || isDecreasing) && differencesOk
}

fun isSafeWithDampener(report: List<Int>): Boolean {
    if (isSafe(report)) {
        return true
    }
    for (i in report.indices) {
        val modifiedReport = report.toMutableList().apply { removeAt(i) }
        if (isSafe(modifiedReport)) {
            return true
        }
    }
    return false
}

