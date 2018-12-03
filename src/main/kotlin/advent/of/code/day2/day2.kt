package advent.of.code.day2

import java.io.File
import java.lang.System.currentTimeMillis as now

fun main(args: Array<String>) {
    val lines = File("src/main/resources/day2/day2input.txt").readLines()

    val start = now()
    val inventory = Day2.inventory(lines)
    val inventoryMs = now() - start
    val diff = Day2.differingIds(lines)
    val diffMs = now() - start + inventoryMs

    println("$inventory ($inventoryMs ms)")
    println("$diff ($diffMs ms)")
}

object Day2 {
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

    tailrec fun differingIds(lines: List<String>): String {
        return if (lines.isEmpty()) ""
        else {
            val comparison = compare(lines, lines.first())
            if (comparison.isNotEmpty()) comparison
            else differingIds(lines.drop(1))
        }
    }

    tailrec fun compare(lines: List<String>, line: String): String {
        return if (lines.isEmpty()) ""
        else {
            val comparison = lines.first()
                    .mapIndexed { index, c ->
                        if (c == line[index]) c else ' '
                    }
            if (comparison.count { it == ' ' } == 1) comparison.joinToString("").replace(" ", "")
            else compare(lines.drop(1), line)
        }
    }
}