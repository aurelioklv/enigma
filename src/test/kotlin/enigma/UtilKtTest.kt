package enigma

import com.aurelioklv.enigma.util.alphabet
import com.aurelioklv.enigma.util.toListOfInt
import com.aurelioklv.enigma.util.toWiring
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UtilKtTest {

    @Test
    fun plugboardWiring() {
        val input = "ZxuF"
        val wiring = input.toWiring("plugboard")

        assertEquals(alphabet.length, wiring.size)
    }

    @Test
    fun rotorWiring() {
        val input = "VZBRGITYUPSDNHLXAWMJQOFECK"

        val wiring = input.toWiring("rotor")
        val expected = alphabet.zip(input).toMap()

        assertEquals(expected, wiring)
    }

    @Test
    fun reflectorWiring() {
        val input = "FVPJIAOYEDRZXWGCTKUQSBNMHL"

        val wiring = input.toWiring("reflector")
        val expected = alphabet.zip(input).toMap()

        assertEquals(expected, wiring)
    }


    @Test
    fun toListOfInt() {
        val input = "bK"
        val output = input.toListOfInt()
        assertEquals(listOf(1, 10), output)
    }
}