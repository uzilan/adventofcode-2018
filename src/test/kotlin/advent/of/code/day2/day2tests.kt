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

        Day2.inventory(input) shouldBe 12
    }

    "Differing ids should return the chars that are similar" {
        val input = """
            abcde
            fghij
            klmno
            pqrst
            fguij
            axcye
            wvxyz
        """.trimIndent().split("\n")

        Day2.differingIds(input) shouldBe "fgij"
    }
})