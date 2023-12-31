package com.aurelioklv.enigma.util

val alphabet = ('A'..'Z').joinToString(separator = "")
val defaultWiring: Map<Char, Char> = ('A'..'Z').associateWith { it }

fun validateWiring(wiring: Map<Char, Char>, kind: String) {
    require(wiring.isNotEmpty()) {
        "Wiring is empty"
    }

    require(wiring.all { (key, value) ->
        key.isLetter() && key.isUpperCase() && value.isLetter() && value.isUpperCase()
    }) {
        "Each character should be an uppercase letter"
    }

    when (kind) {
        in listOf("rotor", "plugboard") -> {
            require(alphabet.all { wiring.containsKey(it) && wiring.containsValue(it) })
        }

        "reflector" -> {
            require(wiring.all { (key, value) -> wiring.getOrDefault(value, ' ') == key }) {
                "Invalid wiring $wiring"
            }
        }
    }
    wiring.mapKeys { it.key.uppercaseChar() }.mapValues { it.value.uppercaseChar() }
}

fun String.toWiring(kind: String, fillMissing: Boolean = false): Map<Char, Char> {
    val input = this.uppercase()
    val result = when (kind) {
        in listOf("rotor", "reflector") -> {
            require(input.length == alphabet.length) {
                "String must be ${alphabet.length} characters long. Got ${input.length}"
            }
            alphabet.zip(input).toMap()
        }

        "plugboard" -> {
            val wiring = input.chunked(2).flatMap { pair ->
                pair.map { it to pair[(pair.indexOf(it) + 1) % 2] }
            }.toMap().toMutableMap()
            val existingChar = input.toSet()
            wiring += defaultWiring.filterKeys { it !in existingChar }
            wiring
        }

        else -> {
            throw IllegalArgumentException("Unknown kind: $kind")
        }
    }
    return result
}

fun String.toListOfInt(): List<Int> {
    return map { char ->
        when {
            char.isLetter() -> char.uppercaseChar().minus('A')
            else -> throw IllegalArgumentException("Invalid character: $char")
        }
    }
}