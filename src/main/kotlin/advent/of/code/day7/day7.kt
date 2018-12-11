package advent.of.code.day7

import java.io.File

fun main(args: Array<String>) {
    val rows = File("src/main/resources/day7/day7input.txt").readLines()
}

object Day7 {
    val regex = "Step (.) must be finished before step (.) can begin.".toRegex()

    fun someOfParts(rows: List<String>): String {
        val steps = rows.map {
            val row = regex.matchEntire(it)?.groupValues!!
            Pair(row[1], row[2])
        }



        return ""
    }
}