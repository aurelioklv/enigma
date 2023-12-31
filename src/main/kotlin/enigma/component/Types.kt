package com.aurelioklv.enigma.component

enum class RotorType(val wiringString: String, val notches: List<Char>) {
    ROTOR_I("EKMFLGDQVZNTOWYHXUSPAIBRCJ", listOf('Q')),
    ROTOR_II("AJDKSIRUXBLHWTMCQGZNPYFVOE", listOf('E')),
    ROTOR_III("BDFHJLCPRTXVZNYEIWGAKMUSQO", listOf('V')),
    ROTOR_IV("ESOVPZJAYQUIRHXLNFTGKDCMWB", listOf('J')),
    ROTOR_V("VZBRGITYUPSDNHLXAWMJQOFECK", listOf('Z')),
    ROTOR_VI("JPGVOUMFYQBENHZRDKASXLICTW", listOf('M', 'Z')),
    ROTOR_VII("NZJHGRCXMYSWBOUFAIVLPEKQDT", listOf('M', 'Z')),
    ROTOR_VIII("FKQHTLXOCBJSPDZRAMEWNIUYGV", listOf('M', 'Z')),
    ROTOR_BETA("LEYJVCNIXWPBQMDRTAKZGFUHOS", listOf('Z')),
}

enum class ReflectorType(val wiringString: String) {
    REFLECTOR_UKW_B_ENIGMA_M3("YRUHQSLDPXNGOKMIEBFZCWVJAT"),
    REFLECTOR_UKW_C_ENIGMA_M3("FVPJIAOYEDRZXWGCTKUQSBNMHL"),
    REFLECTOR_UKW_A_ENIGMA_I("EJMZALYXVBWFCRQUONTSPIKHGD"),
    REFLECTOR_UKW_B_ENIGMA_I("YRUHQSLDPXNGOKMIEBFZCWVJAT"),
    REFLECTOR_UKW_C_ENIGMA_I("FVPJIAOYEDRZXWGCTKUQSBNMHL"),
    REFLECTOR_UKW_B_THIN("ENKQAUYWJICOPBLMDXZVFTHRGS"),
    REFLECTOR_UKW_C_THIN("RDOBJNTKVEHMLFCWZAXGYIPSUQ"),
}