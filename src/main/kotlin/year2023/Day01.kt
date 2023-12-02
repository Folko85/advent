package year2023

import java.io.File

fun main() {

    fun part1() {
        val strings: List<String> = File("src/main/resources/2023_day_1_input.txt").bufferedReader().readLines()
        val sum: Long = strings.stream().mapToLong {
            findNumbers(it)
        }.sum()
        println(sum)
    }

    fun part2() {
        val strings: List<String> = File("src/main/resources/2023_day_1_input.txt").bufferedReader().readLines()
        val sum: Long = strings.stream().mapToLong {
            replaceNumbers(it)
        }.sum()
        println(sum)
    }

    part1()
    part2()
}





fun replaceNumbers(input: String): Long {
    val result: StringBuilder = java.lang.StringBuilder()
    result.append(findFirstNumber(input))
    result.append(findLastNumber(input))
    return result.toString().toLong()
}

fun findNumbers(input: String): Long {
    val result: StringBuilder = java.lang.StringBuilder()
    for (i in input.indices) {
        val element: Char = input[i]
        if (element.isDigit()) {
            result.append(element)
            break
        }
    }
    for (i in input.length - 1 downTo 0) {
        val element: Char = input[i]
        if (element.isDigit()) {
            result.append(element)
            break
        }
    }
    return result.toString().toLong()
}

fun findFirstNumber(input: String): Char {
    for (i in input.indices) {
        val element: Char = input[i]
        if (element.isDigit()) {
            return element
        } else if (element == 'o' && i <= input.length - 3 && input[i + 1] == 'n' && input[i + 2] == 'e') {
            return '1'
        } else if (element == 't' && i <= input.length - 3 && input[i + 1] == 'w' && input[i + 2] == 'o') {
            return '2'
        } else if (element == 't' && i <= input.length - 5 && input[i + 1] == 'h' && input[i + 2] == 'r' && input[i + 3] == 'e' && input[i + 4] == 'e') {
            return '3'
        } else if (element == 'f' && i <= input.length - 4 && input[i + 1] == 'o' && input[i + 2] == 'u' && input[i + 3] == 'r') {
            return '4'
        } else if (element == 'f' && i <= input.length - 4 && input[i + 1] == 'i' && input[i + 2] == 'v' && input[i + 3] == 'e') {
            return '5'
        } else if (element == 's' && i <= input.length - 3 && input[i + 1] == 'i' && input[i + 2] == 'x') {
            return '6'
        } else if (element == 's' && i <= input.length - 5 && input[i + 1] == 'e' && input[i + 2] == 'v' && input[i + 3] == 'e' && input[i + 4] == 'n') {
            return '7'
        } else if (element == 'e' && i <= input.length - 5 && input[i + 1] == 'i' && input[i + 2] == 'g' && input[i + 3] == 'h' && input[i + 4] == 't') {
            return '8'
        } else if (element == 'n' && i <= input.length - 4 && input[i + 1] == 'i' && input[i + 2] == 'n' && input[i + 3] == 'e') {
            return '9'
        }
    }
    return ' '
}

fun findLastNumber(input: String): Char {
    for (i in input.length - 1 downTo 0) {
        val element: Char = input[i]
        if (element.isDigit()) {
            return element
        } else if (element == 'e' && i >= 3 && input[i - 1] == 'n' && input[i - 2] == 'o') {
            return '1'
        } else if (element == 'o' && i >= 3 && input[i - 1] == 'w' && input[i - 2] == 't') {
            return '2'
        } else if (element == 'e' && i >= 5 && input[i - 1] == 'e' && input[i - 2] == 'r' && input[i - 3] == 'h' && input[i - 4] == 't') {
            return '3'
        } else if (element == 'r' && i >= 4 && input[i - 1] == 'u' && input[i - 2] == 'o' && input[i - 3] == 'f') {
            return '4'
        } else if (element == 'e' && i >= 4 && input[i - 1] == 'v' && input[i - 2] == 'i' && input[i - 3] == 'f') {
            return '5'
        } else if (element == 'x' && i >= 3 && input[i - 1] == 'i' && input[i - 2] == 's') {
            return '6'
        } else if (element == 'n' && i >= 5 && input[i - 1] == 'e' && input[i - 2] == 'v' && input[i - 3] == 'e' && input[i - 4] == 's') {
            return '7'
        } else if (element == 't' && i >= 5 && input[i - 1] == 'h' && input[i - 2] == 'g' && input[i - 3] == 'i' && input[i - 4] == 'e') {
            return '8'
        } else if (element == 'e' && i >= 4 && input[i - 1] == 'n' && input[i - 2] == 'i' && input[i - 3] == 'n') {
            return '9'
        }
    }
    return ' '
}