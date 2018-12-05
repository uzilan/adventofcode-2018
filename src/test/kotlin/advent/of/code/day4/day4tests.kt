package advent.of.code.day4

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class OverlapTests : StringSpec({
    val input = """
            [1518-11-01 00:00] Guard #10 begins shift
            [1518-11-01 00:05] falls asleep
            [1518-11-01 00:25] wakes up
            [1518-11-01 00:30] falls asleep
            [1518-11-01 00:55] wakes up
            [1518-11-01 23:58] Guard #99 begins shift
            [1518-11-02 00:40] falls asleep
            [1518-11-02 00:50] wakes up
            [1518-11-03 00:05] Guard #10 begins shift
            [1518-11-03 00:24] falls asleep
            [1518-11-03 00:29] wakes up
            [1518-11-04 00:02] Guard #99 begins shift
            [1518-11-04 00:36] falls asleep
            [1518-11-04 00:46] wakes up
            [1518-11-05 00:55] wakes up
            [1518-11-05 00:03] Guard #99 begins shift
            [1518-11-05 00:45] falls asleep
        """.trimIndent().split("\n")

    "Finding the most likely minute for a guard to sleep should be easy enough" {

        val guardData = Day4.initGuardData(input)

        Day4.strategy1(guardData) shouldBe 240
        Day4.strategy2(guardData) shouldBe 4455
    }
})