package advent.of.code.day6

import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    val rows = File("src/main/resources/day6/day6input.txt").readLines()
    println(Day6.largestArea(rows))
}

object Day6 {
    fun largestArea(rows: List<String>): Int {
        val coords = rows.mapIndexed { index, row -> Coords.from(index, row) }
        val maxX = coords.maxBy { it.x }!!.x
        val maxY = coords.maxBy { it.y }!!.y

        val allCoords = (0..maxX).map { x ->
            (0..maxY).map { y ->
                coordsAt(x, y, coords) ?: findClosestCoord(x, y, coords)
            }
        }.flatten()

        val nonInfinites = coords.filter { !infiniteCoords(allCoords, it, maxX, maxY) }
        val coordCount = nonInfinites.map { coord -> allCoords.count { it.id == coord.id } }
        return coordCount.max()!!
    }

    private fun infiniteCoords(allCoords: List<Coords>, coords: Coords, maxX: Int, maxY: Int): Boolean {
        return allCoords.any { it.id == coords.id && (it.x == 0 || it.x == maxX || it.y == 0 || it.y == maxY) }
    }

    private fun coordsAt(x: Int, y: Int, coords: List<Coords>): Coords? {
        return coords.find { it.x == x && it.y == y }
    }

    private fun findClosestCoord(x: Int, y: Int, coords: List<Coords>): Coords {
        val minDistance = coords
                .groupBy { abs(it.x - x) + abs(it.y - y) }
                .minBy { entry -> entry.key }!!
                .value

        val char = if (minDistance.size > 1) '.' else minDistance.first().id
        return Coords(x, y, char)//minDistance.id.toLowerCase())
    }

    data class Coords(val x: Int, val y: Int, var id: Char) {
        companion object {
            fun from(index: Int, row: String): Coords {
                return row.split(", ").let {
                    Coords(it[0].toInt(), it[1].toInt(), 'A' + index)
                }
            }
        }
    }
}