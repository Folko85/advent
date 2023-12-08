package year2023

import java.io.File

fun main() {


    fun part1() {
        val strings: List<String> = File("src/main/resources/2023_day_8_input.txt").bufferedReader().readLines()
        val mutableMap: MutableMap<String, Pair<String, String>> = mutableMapOf()
        val instructions: List<Char> = strings[0].toCharArray().toList()
        strings.subList(2, strings.size).stream().forEach { string ->
            val valueList: List<String> = string.split("=")
            val key: String = valueList[0].trim()
            val leftRight: List<String> = valueList[1].trim().split(",")
            val left = leftRight[0].filter { it.isLetter() }
            val right = leftRight[1].filter { it.isLetter() }
            mutableMap[key] = Pair(left, right)
        }
        var pointer = "AAA"
        var counter = 0
        var secondCounter = 0
        while (pointer != "ZZZ") {
            val bufferPair: Pair<String, String> = mutableMap[pointer]!!
            val instruction: Char = instructions[counter]
            if (instruction == 'L') {
                pointer = bufferPair.first
            } else if (instruction == 'R') {
                pointer = bufferPair.second
            }
            counter++
            if (counter >= instructions.size) {
                counter = 0
            }
            secondCounter++

        }
        println(secondCounter)

    }

    fun part2() {
        val strings: List<String> = File("src/main/resources/2023_day_6_input.txt").bufferedReader().readLines()

    }

    part1()
//    part2()

}



