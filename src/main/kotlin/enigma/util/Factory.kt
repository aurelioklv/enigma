package com.aurelioklv.enigma.util

import com.aurelioklv.enigma.component.*

object Factory {

    fun createEnigma(
        plugboard: Plugboard,
        rotorTypes: List<RotorType>,
        reflectorType: ReflectorType,
    ): Enigma {
        val rotors: MutableList<Rotor> = mutableListOf()
        rotorTypes.forEach {
            val rotor = createRotor(it)
            rotors.add(rotor)
        }
        val reflector: Reflector = createReflector(reflectorType)
        return Enigma(plugboard, rotors, reflector)
    }

    fun createRotor(rotorType: RotorType): Rotor {
        return Rotor(wiringString = rotorType.wiringString, notches = rotorType.notches, name = rotorType.name)
    }

    fun createReflector(reflectorType: ReflectorType): Reflector {
        return Reflector(wiringString = reflectorType.wiringString, name = reflectorType.name)
    }

    fun createPlugboard(wiringString: String): Plugboard {
        return Plugboard(wiringString = wiringString)
    }
}