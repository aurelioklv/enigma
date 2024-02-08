package com.aurelioklv.enigma.component

class Enigma(
    private val plugboard: Plugboard,
    private val rotors: List<Rotor>,
    private val reflector: Reflector,
    private val name: String = "Enigma Machine",
) {

    fun process(
        input: String,
        removeWhiteSpace: Boolean = false,
        removeNumber: Boolean = false,
        removeSymbol: Boolean = false,
        preserveCase: Boolean = false,
        split: Int = 0,
        verbose: Boolean = false,
    ): String {
        val result = StringBuilder()
        var count = 0
        var ch = ' '

        if (verbose) {
            println(count)
            println(rotorInfo())
        }
        input.forEach {
            if (
                (it.isWhitespace() && (removeWhiteSpace || split != 0)) ||
                (it.isDigit() && removeNumber) ||
                (!it.isLetterOrDigit() && removeSymbol)
            ) {
                return@forEach
            }

            ch = when {
                !it.isLetter() || it.isWhitespace() -> it
                else -> {
                    count++
                    if (verbose) {
                        println("**************************************")
                        println("${count}: $it")
                    }
                    processRotor(verbose)
                    processMessage(it)
                }
            }

            if (preserveCase && it.isLowerCase()) {
                ch = ch.lowercaseChar()
            }

            result.append(ch)
            if (verbose) println("Result: $it -> $ch\n\n")
        }

        val output = result.toString()
        return if (split != 0) output.chunked(split).joinToString(" ") else output
    }

    fun config(
        plugboardWiring: String? = null,
        rotorInitialLetter: List<Char>? = null,
        reflectorInitialLetter: Char? = null,
    ) {
        plugboardWiring?.let {
            plugboard.setWiringMap(it)
        }
        rotorInitialLetter?.let {
            require(it.size == rotors.size) {
                "Initial letter length must be the same as the number of rotor. Expected: ${rotors.size}. Got ${it.size}"
            }
            rotors.forEachIndexed { index, rotor ->
                rotor.setTo(it[index])
            }
        }
        reflectorInitialLetter?.let {
            reflector.setTo(it)
        }
    }

    private fun processRotor(verbose: Boolean = false) {
        var prevIsAtNotch = true
        val todo = StringBuilder()
        rotors.reversed().forEachIndexed { index, it ->
            val currentIsAtNotch = it.isAtNotch()
            when {
                it == rotors.last() -> {
                    todo.append("Todo: Rotating rotor #${index + 1} right: ${it.name}\n")
                    it.rotate()
                }

                it == rotors.first() -> {
                    if (prevIsAtNotch) {
                        it.rotate()
                    }
                }

                else -> {
                    if (prevIsAtNotch || currentIsAtNotch) {
                        todo.append("Todo: Rotating rotor #${index + 1} from right: ${it.name}, cause(prevAtNotch: $prevIsAtNotch, currentAtNotch: $currentIsAtNotch)\n")
                        it.rotate()
                    }
                }
            }
            prevIsAtNotch = currentIsAtNotch
        }
        if (verbose) {
            println(todo.toString())
            println(rotorInfo())
        }
    }

    private fun processMessage(input: Char): Char {
        val plugboardInputSignal = plugboard.forward(input)

        var leftSignal = plugboardInputSignal
        rotors.reversed().forEach {
            leftSignal = it.forward(leftSignal)
        }
        val reflectorSignal = reflector.translate(leftSignal)
        var rightSignal = reflectorSignal
        rotors.forEach {
            rightSignal = it.backward(rightSignal)
        }

        val plugboardOutput = plugboard.backward(rightSignal)
        return plugboardOutput
    }

    fun rotorInfo(): String {
        val info = StringBuilder()
        rotors.reversed().forEachIndexed { index, rotor ->
            info.append("Rotor no ${index + 1} from right, name: ${rotor.name}, letter: ${rotor.getCurrentLetter()}, notch: ${rotor.getNotch()}, step to notch: ${rotor.getStepToNotch()}\n")
        }
        return info.toString()
    }

    override fun toString(): String {
        val rotorNames = rotors.joinToString(",") { it.name }
        return """Machine name: $name
            |Rotors: $rotorNames
            |Plugboard: ${plugboard.name}
        """.trimMargin()
    }
}