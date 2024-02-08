package com.aurelioklv.enigma.component

import com.aurelioklv.enigma.util.alphabet
import com.aurelioklv.enigma.util.toWiring
import com.aurelioklv.enigma.util.validateWiring

class Plugboard(
    private var wiringString: String = alphabet,
    val name: String = "Plugboard",
) {

    private lateinit var wiringMap: Map<Char, Char>

    init {
        setWiringMap(wiringString)
        validateWiring(wiringMap, kind = "plugboard")
    }

    fun setWiringMap(wiringString: String) {
        wiringMap = wiringString.toWiring(kind = "plugboard")
    }

    fun getWiringMap(): Map<Char, Char> {
        return wiringMap
    }

    fun forward(input: Char, verbose: Boolean = false): Int {
        val output = wiringMap[input.uppercaseChar()]
        val outputPosition = alphabet.indexOf(output!!)
        if (verbose) {
            println("$name input char: $input, position: $outputPosition ")
        }
        return outputPosition
    }

    fun backward(position: Int, verbose: Boolean = false): Char {
        val output = wiringMap[alphabet[position]]!!
        if (verbose) {
            println("$name input position: $position. output char: $output")
        }
        return output
    }

    override fun toString(): String {
        return "$name wiring: $wiringMap"
    }
}