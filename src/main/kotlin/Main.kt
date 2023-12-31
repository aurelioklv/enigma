package com.aurelioklv

import com.aurelioklv.enigma.component.ReflectorType
import com.aurelioklv.enigma.component.RotorType
import com.aurelioklv.enigma.util.Factory

fun main() {
    val enigmaI = Factory.createEnigma(
        Factory.createPlugboard(wiringString = "ENIGMA"),
        rotorTypes = listOf(RotorType.ROTOR_V, RotorType.ROTOR_IV, RotorType.ROTOR_II),
        reflectorType = ReflectorType.REFLECTOR_UKW_B_ENIGMA_I
    )
    val enigmaM4 = Factory.createEnigma(
        Factory.createPlugboard(wiringString = "KRYPTOSX"),
        rotorTypes = listOf(RotorType.ROTOR_BETA, RotorType.ROTOR_IV, RotorType.ROTOR_II, RotorType.ROTOR_III),
        reflectorType = ReflectorType.REFLECTOR_UKW_B_THIN
    )

    enigmaI.config(rotorInitialLetter = listOf('D', 'I', 'S'))
    enigmaM4.config(rotorInitialLetter = listOf('C', 'V', 'R', 'Y'))

    val message = "P.Sherman, 42 Wallaby Way, Sydney"

    println(enigmaI.process(message, split = 5, removeSymbol = true))
//    XQIAG AUV42 YTRGR VOQEV UPWLP I

    enigmaI.config(rotorInitialLetter = listOf('D', 'I', 'S'))
    println(enigmaI.process("XQIAG AUV42 YTRGR VOQEV UPWLP I", removeWhiteSpace = true))
//    PSHERMAN42WALLABYWAYSYDNEY

    enigmaM4.config(rotorInitialLetter = listOf('C', 'V', 'R', 'Y'))
    println(enigmaM4.process("nkzljgbeqcsslwpnk", preserveCase = true))
}