package advent.of.code.day4

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun main(args: Array<String>) {
    val lines = File("src/main/resources/day4/day4input.txt").readLines()
    val guardData = Day4.initGuardData(lines)
    println(Day4.strategy1(guardData))
}

object Day4 {
    private val rowRegex = "\\[(.+)\\]\\s(.+)".toRegex()
    private val guardRegex = ".*Guard\\s#(\\d+).*".toRegex()
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    fun initGuardData(lines: List<String>): Map<Int, List<Pair<Long, Row>>> {
        var guard = 0
        val guardData = lines
                .map { Row.from(it) }
                .sortedBy { it.date }
                .map {
                    val groups = guardRegex.matchEntire(it.text)?.groupValues
                    if (groups != null) {
                        guard = groups[1].toInt()
                    }
                    it.copy(guard = guard)
                }
                .filter { !it.text.startsWith("Guard") }
                .groupBy { it.guard }
                .map { (guard, rows) ->
                    guard to rows.mapIndexed { index, row ->
                        if (row.text.startsWith("falls")) Pair(0L, row)
                        else {
                            val minutes = rows[index - 1].date.until(rows[index].date, ChronoUnit.MINUTES)
                            Pair(minutes, row)
                        }
                    }
                }.toMap()
        return guardData
    }

    fun strategy1(guardData: Map<Int, List<Pair<Long, Row>>>): Long {
        val sleepiestGuard = guardData.maxBy { (_, rowList) ->
            rowList.sumBy { (duration, _) -> duration.toInt() }
        }

        val sleepiestMinute = sleepiestGuard?.value
                ?.filter { pair -> pair.second.text.startsWith("wakes") }
                ?.flatMap { (duration, row) -> ((row.date.minute - duration) until row.date.minute).toList() }
                ?.groupBy { it }
                ?.maxBy { it.value.size }
                ?.key

        return sleepiestMinute!! * sleepiestGuard.key
    }

    data class Row(val date: LocalDateTime, val text: String, var guard: Int = 0) {

        companion object {
            fun from(line: String): Row {
                val groups = Day4.rowRegex.matchEntire(line)!!.groupValues
                val date: LocalDateTime = LocalDateTime.parse(groups[1], dateFormatter)
                val text = groups[2]
                return Row(date, text)
            }
        }
    }
}