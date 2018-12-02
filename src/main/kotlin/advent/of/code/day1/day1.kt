package advent.of.code.day1

import java.io.File

fun main(args: Array<String>) {
    val lines = File("src/main/resources/day1input.txt").readLines()
    println(calibrate(lines))
}

fun calibrate(lines: List<String>): Int {
    return lines.fold(0) { acc, curr ->
        val sign = curr.first()
        val calibration = curr.drop(1).toInt()
        if (sign == '+') acc + calibration
        else acc - calibration
    }
}

