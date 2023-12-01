package twenty_three.one

import java.io.File

fun main() {
    val strings: List<String> = File("src/main/resources/2023_day_1_input.txt").bufferedReader().readLines()
    var sum: Long = 0
    var number: StringBuilder = java.lang.StringBuilder()
    strings.forEach {
        for (i in it.indices) {
            val element: Char = it[i]
            if (element.isDigit()) {
                number.append(element)
                break
            }
        }
        for (i in it.length - 1 downTo  0){
            val element: Char = it[i]
            if (element.isDigit()) {
                number.append(element)
                break
            }
        }
        sum += number.toString().toLong()
        number = java.lang.StringBuilder()
    }

    println(sum)

}