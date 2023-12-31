package com.aurelioklv.enigma.component

import com.aurelioklv.enigma.util.alphabet
import com.aurelioklv.enigma.util.toWiring
import com.aurelioklv.enigma.util.validateWiring

class Rotor(
    private val wiringString: String,
    private var initialLetter: Char = 'A',
    private val notches: List<Char>,
    val name: String = "Rotor",
) {
    private var currentLetter: Char = '\u0000'
    private var currentPosition: Int = 0
    private var wiringMap: Map<Char, Char> = wiringString.toWiring(kind = "rotor")
    private lateinit var rotorPairs: MutableList<Pair<Char, Char>>
    private var stepToNotch = emptyMap<Char, Int>()

    init {
        validateWiring(wiringMap, kind = "rotor")
        reset()
        setTo(initialLetter)
    }

    private fun reset() {
        rotorPairs = wiringMap.toList().toMutableList()
        currentLetter = alphabet.first()
        currentPosition = 0
    }

    fun forward(position: Int, verbose: Boolean = false): Int {
        val (input, output) = rotorPairs[position]
        val outputPosition = rotorPairs.indexOfFirst { it.first == output }
        if (verbose) {
            println("$name input at $position: $input, output at $outputPosition: $output")
        }
        return outputPosition
    }

    fun backward(position: Int, verbose: Boolean = false): Int {
        val (output, input) = rotorPairs[position]
        val outputPosition = rotorPairs.indexOfFirst { it.second == output }
        if (verbose) {
            println("$name input at $position: $input, output at $outputPosition: $output")
        }
        return outputPosition
    }

    fun setTo(letter: Char) {
        val input = letter.uppercaseChar()
        initialLetter = input
        reset()

        val position = alphabet.indexOf(input)
        rotate(position)
    }

    fun getWiringMap(): Map<Char, Char> {
        return wiringMap
    }

    fun getCurrentLetter(): Char {
        return currentLetter
    }

    fun isAtNotch(): Boolean {
        return notches.contains(currentLetter)
    }

    fun getNotch(): List<Char> {
        return notches
    }

    fun getStepToNotch(): Map<Char, Int> {
        return stepToNotch
    }

    fun rotate(step: Int = 1) {
        rotorPairs = rotorPairs.drop(step).plus(rotorPairs.take(step)).toMutableList()

        currentLetter = rotorPairs.first().first

        currentPosition = alphabet.indexOf(currentLetter)

        stepToNotch = notches.associateWith { (it.plus(26).minus(currentLetter).mod(26)) }
    }

    override fun toString(): String {
        return """Rotor
            |Name: $name
            |Current pair: $rotorPairs
            |Current letter: $currentLetter
            |Current position: $currentPosition
            |Initial position: $initialLetter
            |Notch(s): $notches
            |Is at notch: ${isAtNotch()}
            |Next notch: $stepToNotch
            |WiringString: $wiringString
            |WiringMap: $wiringMap
        """.trimMargin()
    }
}