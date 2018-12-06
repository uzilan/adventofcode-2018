package advent.of.code.day5

import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    val line = File("src/main/resources/day5/day5input.txt").readLines()[0]
    println(Day5.reduce(line))
    println(Day5.removeAndReduce(line))
}

object Day5 {

    fun reduce(input: String): Int {
        var i = 0
        var line = input
        while (true) {
            if (i == line.length - 2) return line.length
            if (abs(line[i] - line[i + 1]) == 32) {
                line = line.take(i) + line.drop(i + 2)
                if (i > 0) i -= 1
            } else i++
        }
    }

    fun removeAndReduce(line: String): Int {
        return ('a'..'z')
                .map { line.replace(it.toString(), "").replace(it.toUpperCase().toString(), "") }
                .map { reduce(it) }
                .min()!!
    }
}