package year2025

import java.io.File

fun main() {

    fun part1() {
        var result = 0L
        val input: String = File("src/main/resources/year2025/day2_input.txt").bufferedReader().readLine()
        val intervalStrings: List<String> = input.split(",".toRegex())
        val ranges: MutableList<LongRange> = mutableListOf()
        intervalStrings.forEach { interval ->
            val intervalLong: List<Long> = interval.split("-".toRegex()).map {
                it.toLong()
            }
            ranges.add(LongRange(intervalLong[0], intervalLong[1]))
        }
        ranges.forEach { range ->
            range.forEach { value ->
                var mirrorFlag: Boolean
                val valueString = value.toString()
                val stringSize = valueString.length
                if (stringSize % 2 == 0) {
                    mirrorFlag = true
                    for (i in 0..<stringSize / 2) {
                        if (valueString[i] != valueString[stringSize / 2 + i]) {
                            mirrorFlag = false
                            break
                        }
                    }
                    if (mirrorFlag) {
                        result += value
                    }
                }
            }
        }
        println(result)
    }

    fun part2() {
        var result = 0L
        val input: String = File("src/main/resources/year2025/day2_input.txt").bufferedReader().readLine()
        val intervalStrings: List<String> = input.split(",".toRegex())
        val ranges: MutableList<LongRange> = mutableListOf()
        intervalStrings.forEach { interval ->
            val intervalLong: List<Long> = interval.split("-".toRegex()).map {
                it.toLong()
            }
            ranges.add(LongRange(intervalLong[0], intervalLong[1]))
        }
        ranges.forEach { range ->
            range.forEach { value ->
                var mirrorFlag = false
                val valueString = value.toString()
                val stringSize = valueString.length
                if (stringSize % 2 == 0) {
                    mirrorFlag = true
                    for (i in 0..<stringSize / 2) {
                        if (valueString[i] != valueString[stringSize / 2 + i]) {
                            mirrorFlag = false
                            break
                        }
                    }
                    if (mirrorFlag) {
                        result += value
                    }
                }
                if (!mirrorFlag && stringSize % 3 == 0) {
                    mirrorFlag = true
                    val delimiterThree = stringSize / 3
                    for (i in 0..<delimiterThree) {
                        val elements: MutableList<Char> = mutableListOf()
                        elements.add(valueString[i])
                        elements.add(valueString[delimiterThree + i])
                        elements.add(valueString[(delimiterThree * 2) + i])
                        if (!elements.all { it == elements[0] }) {
                            mirrorFlag = false
                            break
                        }
                    }
                    if (mirrorFlag) {
                        result += value
                    }
                }
                if (!mirrorFlag && stringSize % 4 == 0) {
                    mirrorFlag = true
                    val delimiterFour = stringSize / 4
                    for (i in 0..<delimiterFour) {
                        val elements: MutableList<Char> = mutableListOf()
                        elements.add(valueString[i])
                        elements.add(valueString[delimiterFour + i])
                        elements.add(valueString[(delimiterFour * 2) + i])
                        elements.add(valueString[(delimiterFour * 3) + i])
                        if (!elements.all { it == elements[0] }) {
                            mirrorFlag = false
                            break
                        }
                    }
                    if (mirrorFlag) {
                        result += value
                    }
                }
                if (!mirrorFlag && stringSize % 5 == 0) {
                    mirrorFlag = true
                    val delimiterFive = stringSize / 5
                    for (i in 0..<delimiterFive) {
                        val elements: MutableList<Char> = mutableListOf()
                        elements.add(valueString[i])
                        elements.add(valueString[delimiterFive + i])
                        elements.add(valueString[(delimiterFive * 2) + i])
                        elements.add(valueString[(delimiterFive * 3) + i])
                        elements.add(valueString[(delimiterFive * 4) + i])
                        if (!elements.all { it == elements[0] }) {
                            mirrorFlag = false
                            break
                        }
                    }
                    if (mirrorFlag) {
                        result += value
                    }
                }
                if (!mirrorFlag && stringSize % 6 == 0) {
                    mirrorFlag = true
                    val delimiterSix = stringSize / 6
                    for (i in 0..<delimiterSix) {
                        val elements: MutableList<Char> = mutableListOf()
                        elements.add(valueString[i])
                        elements.add(valueString[delimiterSix + i])
                        elements.add(valueString[(delimiterSix * 2) + i])
                        elements.add(valueString[(delimiterSix * 3) + i])
                        elements.add(valueString[(delimiterSix * 4) + i])
                        elements.add(valueString[(delimiterSix * 5) + i])
                        if (!elements.all { it == elements[0] }) {
                            mirrorFlag = false
                            break
                        }
                    }
                    if (mirrorFlag) {
                        result += value
                    }
                }
                if (!mirrorFlag && stringSize % 7 == 0) {
                    mirrorFlag = true
                    val delimiterSeven = stringSize / 7
                    for (i in 0..<delimiterSeven) {
                        val elements: MutableList<Char> = mutableListOf()
                        elements.add(valueString[i])
                        elements.add(valueString[delimiterSeven + i])
                        elements.add(valueString[(delimiterSeven * 2) + i])
                        elements.add(valueString[(delimiterSeven * 3) + i])
                        elements.add(valueString[(delimiterSeven * 4) + i])
                        elements.add(valueString[(delimiterSeven * 5) + i])
                        elements.add(valueString[(delimiterSeven * 6) + i])
                        if (!elements.all { it == elements[0] }) {
                            mirrorFlag = false
                            break
                        }
                    }
                    if (mirrorFlag) {
                        result += value
                    }
                }
                if (!mirrorFlag && stringSize % 8 == 0) {
                    mirrorFlag = true
                    val delimiterEight = stringSize / 8
                    for (i in 0..<delimiterEight) {
                        val elements: MutableList<Char> = mutableListOf()
                        elements.add(valueString[i])
                        elements.add(valueString[delimiterEight + i])
                        elements.add(valueString[(delimiterEight * 2) + i])
                        elements.add(valueString[(delimiterEight * 3) + i])
                        elements.add(valueString[(delimiterEight * 4) + i])
                        elements.add(valueString[(delimiterEight * 5) + i])
                        elements.add(valueString[(delimiterEight * 6) + i])
                        elements.add(valueString[(delimiterEight * 7) + i])
                        if (!elements.all { it == elements[0] }) {
                            mirrorFlag = false
                            break
                        }
                    }
                    if (mirrorFlag) {
                        result += value
                    }
                }
                if (!mirrorFlag && stringSize % 9 == 0) {
                    mirrorFlag = true
                    val delimiterNine = stringSize / 9
                    for (i in 0..<delimiterNine) {
                        val elements: MutableList<Char> = mutableListOf()
                        elements.add(valueString[i])
                        elements.add(valueString[delimiterNine + i])
                        elements.add(valueString[(delimiterNine * 2) + i])
                        elements.add(valueString[(delimiterNine * 3) + i])
                        elements.add(valueString[(delimiterNine * 4) + i])
                        elements.add(valueString[(delimiterNine * 5) + i])
                        elements.add(valueString[(delimiterNine * 6) + i])
                        elements.add(valueString[(delimiterNine * 7) + i])
                        elements.add(valueString[(delimiterNine * 8) + i])
                        if (!elements.all { it == elements[0] }) {
                            mirrorFlag = false
                            break
                        }
                    }
                    if (mirrorFlag) {
                        result += value
                    }
                }
                if (!mirrorFlag && stringSize % 10 == 0) {
                    mirrorFlag = true
                    val delimiterTen = stringSize / 10
                    for (i in 0..<delimiterTen) {
                        val elements: MutableList<Char> = mutableListOf()
                        elements.add(valueString[i])
                        elements.add(valueString[delimiterTen + i])
                        elements.add(valueString[(delimiterTen * 2) + i])
                        elements.add(valueString[(delimiterTen * 3) + i])
                        elements.add(valueString[(delimiterTen * 4) + i])
                        elements.add(valueString[(delimiterTen * 5) + i])
                        elements.add(valueString[(delimiterTen * 6) + i])
                        elements.add(valueString[(delimiterTen * 7) + i])
                        elements.add(valueString[(delimiterTen * 8) + i])
                        elements.add(valueString[(delimiterTen * 9) + i])
                        if (!elements.all { it == elements[0] }) {
                            mirrorFlag = false
                            break
                        }
                    }
                    if (mirrorFlag) {
                        result += value
                    }
                }
            }
        }
        println(result)
    }

    part1()
    part2()
}