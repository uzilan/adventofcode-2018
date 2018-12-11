package advent.of.code.day10

import java.io.File

fun main(args: Array<String>) {
    val rows = File("src/main/resources/day10/day10input.txt").readLines()
    Day10.init(rows)
    println(Day10.mkString())

//    (0..10).forEach {
//        state.oneMoreSecond()
//        println(state)
//    }
}

object Day10 {
    val n = "(\\s*-?\\d+)"
    val regex = "position=<$n,$n>\\svelocity=<$n,$n>".toRegex()
    var meta: Meta = Meta()
    var state: List<Row> = listOf()

    fun init(input: List<String>) {
        state = input.map { row ->
            val groups = regex.matchEntire(row)!!.groupValues
            (1..4).map { groups[it].trim().toInt() }
        }.map { Row(it[0], it[1], it[2], it[3]) }

        val xs = state.map { it.x }.sorted()
        val ys = state.map { it.y }.sorted()

        val minX = xs.first()
        val maxX = xs.last()
        val minY = ys.first()
        val maxY = ys.last()
        val xLength = maxX - minX + 1

        meta = Meta(xs, ys, minX, maxX, minY, maxY, xLength)
    }

    fun oneMoreSecond() {
        state.forEach {
            it.x += it.dx
            it.y += it.dy
        }
    }

    fun mkString(): String {
        val grouped = state.groupBy { it.y }
        val keys = grouped.keys

        return (meta.minY..meta.maxY).fold("") { acc, curr ->
            acc + if (!keys.contains(curr)) {
                ".".repeat(meta.xLength)
            } else {
                (meta.minX..meta.maxX).map { x ->
                    if (grouped[curr]!!.any {
                                it.x == x
                            }) "#" else "."
                }.joinToString("")
            } + "\n"
        }.trim()
    }

    data class Meta(val xs: List<Int> = listOf(),
                    val ys: List<Int> = listOf(),
                    val minX: Int = 0,
                    val maxX: Int = 0,
                    val minY: Int = 0,
                    val maxY: Int = 0,
                    val xLength: Int = 0)

    data class Row(var x: Int, var y: Int, val dx: Int, val dy: Int)
}
