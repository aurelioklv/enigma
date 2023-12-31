package enigma.util

import com.aurelioklv.enigma.util.toListOfInt
import com.aurelioklv.enigma.util.toWiring
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class UtilKtTest {

    @Test
    fun toMap() {
        val input = "ZxuF"
        val output = input.toWiring()
        assertEquals(mapOf('Z' to 'x', 'u' to 'F'), output)
    }

    @Test
    fun toListOfInt() {
        val input = "bK"
        val output = input.toListOfInt()
        assertEquals(listOf(1, 10), output)
    }
}