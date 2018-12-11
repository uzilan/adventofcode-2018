package advent.of.code.day7

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ChronalCoordinatesTests : StringSpec({
    "Polymers should reduce" {
        val rows = """
            Step C must be finished before step A can begin.
            Step C must be finished before step F can begin.
            Step A must be finished before step B can begin.
            Step A must be finished before step D can begin.
            Step B must be finished before step E can begin.
            Step D must be finished before step E can begin.
            Step F must be finished before step E can begin.
        """.trimIndent().split("\n")

        Day7.someOfParts(rows) shouldBe "CABDFE"
    }
})