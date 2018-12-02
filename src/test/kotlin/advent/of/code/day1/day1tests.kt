package advent.of.code.day1

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class CalibrationTests : StringSpec({
    "Calibration should result in correct number" {
        forall(
                row(listOf("+1", "-2", "+3", "+1"), 3),
                row(listOf("+1", "+1", "+1"), 3),
                row(listOf("+1", "+1", "-2"), 0),
                row(listOf("-1", "-2", "-3"), -6)
        ) { strings, result ->
            calibrate(strings) shouldBe result
        }
    }
})