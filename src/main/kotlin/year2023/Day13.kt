package year2023

import utils.Utils
import java.io.File

fun main() {

    fun findMirror(array: Array<CharArray>): Int {
        val candidates: MutableList<Int> = mutableListOf()
        for (i in 0..<array.size - 1) {
            if (array[i].contentEquals(array[i + 1])) {
                candidates.add(i + 1)
            }
        }
        if (candidates.size == 1) {
            return candidates[0]
        } else {
            val target = array.size / 2
            val answer = candidates.fold(null){acc: Int?, num ->
                if(num <= target && (acc == null || num > acc)) num
                else acc

            }
            if (answer != null){
                return answer
            }
        }
        return 0
    }


    fun formArray(pattern: MutableList<String>, structure: MutableList<Array<CharArray>>) {
        val rows: Int = pattern.size
        val cols: Int = pattern[0].length
        val array: Array<CharArray> = Array(rows) { CharArray(cols) }
        for (i in 0..<rows) {
            array[i] = pattern[i].toCharArray()
        }
        structure.add(array)
    }

    fun calculateStructure(structure: Array<CharArray>): Int {
        val horizontal = findMirror(structure)
        val rotatedArray: Array<CharArray> = Utils.rotateClockwise(structure)
        val vertical = findMirror(rotatedArray)
        val horizontalDelta = if (structure.size - horizontal > horizontal) horizontal else structure.size - horizontal
        val verticalDelta = if (rotatedArray.size - vertical > vertical) vertical else rotatedArray.size - vertical
        return if (verticalDelta > horizontalDelta) vertical else horizontal * 100
    }

    fun part1() {
        val strings: List<String> = File("src/main/resources/2023_day_13_input.txt").bufferedReader().readLines()
        val structure: MutableList<Array<CharArray>> = mutableListOf()
        var pattern: MutableList<String> = mutableListOf()
        strings.forEach {
            if (it.isNotEmpty()) {
                pattern.add(it)
            } else {
                formArray(pattern, structure)
                pattern = mutableListOf()
            }
        }
        formArray(pattern, structure)
        var sum = 0L
        structure.forEach { struct ->
            sum += calculateStructure(struct)
        }

        println(sum)

    }

//    fun part2() {
//        val strings: List<String> = File("src/main/resources/2023_day_11_input.txt").bufferedReader().readLines()
//    }

    part1()
//    part2()

}





