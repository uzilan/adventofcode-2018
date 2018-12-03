package advent.of.code.day3

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class OverlapTests : StringSpec({
    "Lines should be parsed correctly" {
        forall(
                row("#1 @ 1,3: 4x4", 1, 3, 4, 4),
                row("#2 @ 3,1: 4x4", 3, 1, 4, 4),
                row("#3 @ 5,5: 2x2", 5, 5, 2, 2)
        ) { string, left, top, width, height ->
            val (l, t, w, h) = Day3.parseLine(string)
            l shouldBe left
            t shouldBe top
            w shouldBe width
            h shouldBe height
        }
    }

    "Claims should be overlapping" {
        val input = """
            #1 @ 1,3: 4x4
            #2 @ 3,1: 4x4
            #3 @ 5,5: 2x2
        """.trimIndent().split("\n")

        Day3.overlapping(input) shouldBe 4
    }
})