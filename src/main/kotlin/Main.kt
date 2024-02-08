package com.aurelioklv

import com.aurelioklv.enigma.component.ReflectorType
import com.aurelioklv.enigma.component.RotorType
import com.aurelioklv.enigma.util.Factory

fun main() {
    val enigmaI = Factory.createEnigma(
        Factory.createPlugboard(wiringString = "vbnmhjklasdfqwertyui"),
        rotorTypes = listOf(RotorType.ROTOR_V, RotorType.ROTOR_I, RotorType.ROTOR_V),
        reflectorType = ReflectorType.REFLECTOR_UKW_B_ENIGMA_I
    )
    val enigmaM3 = Factory.createEnigma(
        Factory.createPlugboard(wiringString = "qawsedrftgyhujikolpz"),
        rotorTypes = listOf(RotorType.ROTOR_VIII, RotorType.ROTOR_VII, RotorType.ROTOR_IV),
        reflectorType = ReflectorType.REFLECTOR_UKW_C_ENIGMA_M3
    )
    val enigmaNorenigma = Factory.createEnigma(
        Factory.createPlugboard(wiringString = "plokijuhygtfrdeswa"),
        rotorTypes = listOf(RotorType.ROTOR_V_NORENIGMA, RotorType.ROTOR_II_NORENIGMA, RotorType.ROTOR_III_NORENIGMA),
        reflectorType = ReflectorType.REFLECTOR_UKW_NORENIGMA
    )
    val enigmaM4 = Factory.createEnigma(
        Factory.createPlugboard(wiringString = "KRYPTOSX"),
        rotorTypes = listOf(RotorType.ROTOR_GAMMA, RotorType.ROTOR_VI, RotorType.ROTOR_II, RotorType.ROTOR_III),
        reflectorType = ReflectorType.REFLECTOR_UKW_C_THIN
    )
    val enigmaSonder = Factory.createEnigma(
        Factory.createPlugboard(wiringString = "SECRXTINFO"),
        rotorTypes = listOf(RotorType.ROTOR_III_SONDER, RotorType.ROTOR_I_SONDER, RotorType.ROTOR_II_SONDER),
        reflectorType = ReflectorType.REFLECTOR_UKW_SONDER
    )

    val message = "P.Sherman, 42 Wallaby Way, Sydney"


    enigmaI.config(rotorInitialLetter = listOf('D', 'I', 'S'))
    println(enigmaI.process(message, split = 5, removeSymbol = true))
//    NFNDP YJW42 OYCQF ZZUXA KVKUY G

    enigmaI.config(rotorInitialLetter = listOf('D', 'I', 'S'))
    println(enigmaI.process("NFNDP YJW42 OYCQF ZZUXA KVKUY G", removeWhiteSpace = true))
//    PSHERMAN42WALLABYWAYSYDNEY

    enigmaM4.config(rotorInitialLetter = listOf('C', 'V', 'R', 'Y'))
    println(enigmaM4.process("ikhijfnezi4cmwt3i", preserveCase = true))
//    ??🗿
}