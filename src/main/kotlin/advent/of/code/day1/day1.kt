package advent.of.code.day1

import java.io.File

fun main(args: Array<String>) {
    val lines = File("src/main/resources/day1/day1input.txt").readLines()
    println(Day1.calibrate(lines))
    println(Day1.chronalCalibration(lines, lines))
}

object Day1 {
    fun calibrate(lines: List<String>): Int {
        return lines.fold(0) { acc, curr ->
            acc + nextInt(curr)
        }
    }

    tailrec fun chronalCalibration(
            allLines: List<String>,
            remainingLines: List<String>,
            currentResult: Int = 0,
            foundNumbers: List<Int> = listOf(0)): Int {
        val nextInt = currentResult + nextInt(remainingLines.first())
        return if (foundNumbers.contains(nextInt)) nextInt
        else {
            val nextLines = if (remainingLines.size > 1) remainingLines.drop(1) else allLines
            chronalCalibration(
                    allLines,
                    nextLines,
                    nextInt,
                    foundNumbers + nextInt)
        }
    }

    fun nextInt(curr: String): Int {
        val sign = curr.first()
        val calibration = curr.drop(1).toInt()
        return if (sign == '+') return calibration
        else -calibration
    }
}

