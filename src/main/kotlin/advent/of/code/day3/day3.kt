package advent.of.code.day3

import java.io.File

fun main(args: Array<String>) {
    val lines = File("src/main/resources/day3/day3input.txt").readLines()
    println(Day3.overlapping(lines))
}

object Day3 {
    private val regex = "#(\\d+)\\s@\\s(\\d+),(\\d+): (\\d+)x(\\d+)".toRegex()
    private val distinctPairs = emptySet<Pair<Int, Int>>().toMutableSet()
    private val overlappingPairs = emptySet<Pair<Int, Int>>().toMutableSet()

    fun overlapping(lines: List<String>): Int {
        // find all coordinates in each line
        lines.forEach { line ->
            val claim = Claim(line)

            // create pairs for every coordinate
            (claim.left..(claim.left + claim.width - 1)).forEach { x ->
                (claim.top..(claim.top + claim.height - 1)).forEach { y ->
                    val pair = Pair(x, y)
                    if (distinctPairs.contains(pair)) overlappingPairs += pair
                    else distinctPairs.add(pair)
                }
            }
        }
        return overlappingPairs.size
    }

    class Claim(line: String) {
        private val groups = regex.matchEntire(line)!!.groupValues
        val id = groups[1]
        val left = groups[2].toInt()
        val top = groups[3].toInt()
        val width = groups[4].toInt()
        val height = groups[5].toInt()
    }
}