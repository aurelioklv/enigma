package com.aurelioklv.enigma.component

import com.aurelioklv.enigma.util.alphabet
import com.aurelioklv.enigma.util.toWiring
import com.aurelioklv.enigma.util.validateWiring

class Reflector(
    private var wiringString: String,
    private var initialLetter: Char = 'A',
    val name: String = "Reflector",
) {

    private var currentLetter: Char = '\u0000'
    private var currentPosition: Int = 0
    private var wiringMap: Map<Char, Char> = wiringString.toWiring(kind = "reflector")
    private lateinit var reflectorPair: MutableList<Pair<Char, Char>>

    init {
        validateWiring(wiringMap, kind = "reflector")
        reset()
        setTo(initialLetter)
    }

    private fun reset() {
        reflectorPair = wiringMap.toList().toMutableList()
        currentLetter = alphabet.first()
        currentPosition = 0
    }

    fun translate(position: Int, verbose: Boolean = false): Int {
        val (input, output) = reflectorPair[position]
        val outputPosition = reflectorPair.indexOfFirst { it.first == output }
        if (verbose) {
            println("$name input: ($position = $input), output: ($outputPosition, $output)")
        }
        return outputPosition
    }

    fun setTo(letter: Char) {
        initialLetter = letter
        reset()

        val position = alphabet.indexOf(letter)
        rotate(position)
    }

    fun getWiringMap(): Map<Char, Char> {
        return wiringMap
    }

    private fun rotate(step: Int = 1) {
        reflectorPair = reflectorPair.drop(step).plus(reflectorPair.take(step)).toMutableList()

        currentLetter = reflectorPair.first().first

        currentPosition = alphabet.indexOf(currentLetter)
    }
}