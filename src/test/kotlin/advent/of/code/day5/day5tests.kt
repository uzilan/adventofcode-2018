package advent.of.code.day5

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class InventoryTests : StringSpec({
    "Polymers should reduce" {
        Day5.reduce("dabAcCaCBAcCcaDA","dabAcCaCBAcCcaDA") shouldBe "dabCBAcaDA".length
    }
})