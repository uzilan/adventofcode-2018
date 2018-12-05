package advent.of.code.day3

import java.io.File

fun main(args: Array<String>) {
    val lines = File("src/main/resources/day3/day3input.txt").readLines()
    println(Day3.Part1.overlapping(lines))
    println(Day3.Part2.nonOverlapping(lines))
}

object Day3 {
    private val regex = "#(\\d+)\\s@\\s(\\d+),(\\d+): (\\d+)x(\\d+)".toRegex()

    object Part1 {
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
    }

    object Part2 {
        private val distincts = emptySet<Triple<Int, Int, Int>>().toMutableSet()
        private val ids = emptySet<Int>().toMutableSet()

        fun nonOverlapping(lines: List<String>): Int {
            lines.forEach { line ->
                val claim = Claim(line)
                ids += claim.id

                (claim.left..(claim.left + claim.width - 1)).forEach { x ->
                    (claim.top..(claim.top + claim.height - 1)).forEach { y ->

                        val maybeOverlapping = distincts.find { it.second == x && it.third == y }

                        if (maybeOverlapping == null) {
                            distincts.add(Triple(claim.id, x, y))
                        } else {
                            ids -= claim.id
                            ids -= maybeOverlapping.first
                        }
                    }
                }
            }
            return ids.first()
        }
    }

    class Claim(line: String) {
        private val groups = regex.matchEntire(line)!!.groupValues
        val id = groups[1].toInt()
        val left = groups[2].toInt()
        val top = groups[3].toInt()
        val width = groups[4].toInt()
        val height = groups[5].toInt()
    }
}