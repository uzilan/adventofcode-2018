package advent.of.code.day5

import java.io.File

fun main(args: Array<String>) {
    val line = File("src/main/resources/day5/day5input.txt").readLines()[0]
    println(Day5.reduce(line, line))
}

object Day5 {
    tailrec fun reduce(theWholeLine: String, line: String, result: Int = 0): Int {
        return when {
            line.isEmpty() -> result
            line.length == 1 -> result + 1
            else -> {
                if (areCharsSameButDifferentCase(line[0], line[1])) {
                    val newWholeLine = theWholeLine.take(theWholeLine.length - line.length) + line.drop(2)
                    reduce(newWholeLine, newWholeLine, 0)
                } else reduce(theWholeLine, line.drop(1), result + 1)
            }
        }
    }

    private fun areCharsSameButDifferentCase(first: Char, second: Char) = first.toLowerCase() == second.toLowerCase() &&
            ((first.isLowerCase() && second.isUpperCase()) || (first.isUpperCase() && second.isLowerCase()))
}