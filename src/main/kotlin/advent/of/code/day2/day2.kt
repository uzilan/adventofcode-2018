package advent.of.code.day2

import java.io.File

fun main(args: Array<String>) {
    val lines = File("src/main/resources/day2/day2input.txt").readLines()
    println(inventory(lines))
}

fun inventory(lines: List<String>): Int {
    val (doubles, triplets) = lines
            .fold(Pair(0, 0)) { acc, curr ->
                val frequencies = curr.groupBy { it }.values.map { it.size }
                val doubles = if (frequencies.any { it == 2 }) 1 else 0
                val triplets = if (frequencies.any { it == 3 }) 1 else 0
                Pair(acc.first + doubles, acc.second + triplets)
            }
    return doubles * triplets
}

