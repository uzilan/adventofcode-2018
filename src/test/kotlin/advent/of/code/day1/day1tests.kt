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
            Day1.calibrate(strings) shouldBe result
        }
    }

    "Chronal calibration should result in correct number" {
        forall(
                row(listOf("+1", "-2", "+3", "+1"), 2),
                row(listOf("-1", "+1"), 0),
                row(listOf("+3", "+3", "+4", "-2", "-4"), 10),
                row(listOf("-6", "+3", "+8", "+5", "-6"), 5),
                row(listOf("+7", "+7", "-2", "-7", "-4"), 14)
        ) { strings, result ->
            Day1.chronalCalibration(strings, strings) shouldBe result
        }
    }
})