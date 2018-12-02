package advent.of.code.day2

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class InventoryTests : StringSpec({
    "Inventory should result in correct number" {
        val input = """
            abcdef
            bababc
            abbcde
            abcccd
            aabcdd
            abcdee
            ababab
        """.trimIndent().split("\n")

        inventory(input) shouldBe 12
    }
})