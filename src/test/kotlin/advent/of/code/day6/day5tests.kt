package advent.of.code.day6

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ChronalCoordinatesTests : StringSpec({
    "Polymers should reduce" {
        val rows = """
            1, 1
            1, 6
            8, 3
            3, 4
            5, 5
            8, 9
        """.trimIndent().split("\n")

        Day6.largestArea(rows) shouldBe 17
    }
})