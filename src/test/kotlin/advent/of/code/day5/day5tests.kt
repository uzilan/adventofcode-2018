package advent.of.code.day5

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class InventoryTests : StringSpec({
    "Polymers should reduce" {
        Day5.reduce("dabAcCaCBAcCcaDA") shouldBe "dabCBAcaDA".length
    }

    "Improving polymer should give a shorter result" {
        Day5.removeAndReduce("dabAcCaCBAcCcaDA") shouldBe "daDA".length
    }
})