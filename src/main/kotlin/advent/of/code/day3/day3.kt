package advent.of.code.day3

import java.io.File

fun main(args: Array<String>) {
    val lines = File("src/main/resources/day3/day3input.txt").readLines()
    println(Day3.overlapping(lines))
}

object Day3 {

    private val regex = ".*@ (\\d+),(\\d+): (\\d+)x(\\d+)".toRegex()
    private val distinctPairs = emptySet<Pair<Int, Int>>().toMutableSet()
    private val overlappingPairs = emptySet<Pair<Int, Int>>().toMutableSet()

    fun overlapping(lines: List<String>): Int {
        // find all coordinates in each line
        lines.forEach { line ->
            val (left, top, width, height) = parseLine(line)

            // create pairs for every coordinate
            (left..(left + width - 1)).forEach { x ->
                (top..(top + height - 1)).forEach { y ->
                    val pair = Pair(x, y)
                    if (distinctPairs.contains(pair)) overlappingPairs += pair
                    else distinctPairs.add(pair)
                }
            }
        }
        return overlappingPairs.size
    }

    fun parseLine(line: String): List<Int> {
        return regex.matchEntire(line)!!.groupValues // match groups
                .drop(1) // the first group is the whole line. Drop it
                .map { it.toInt() } // convert every group into an int
    }
}