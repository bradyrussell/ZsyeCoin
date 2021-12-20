package com.adrianishmael.zsyecoin

import io.github.netvl.ecoji.Ecoji

class Encoding {
    companion object {
        fun encode(data: ByteArray): String
        {
            return Ecoji.getEncoder().readFrom(data).writeToString()
        }

        fun decode(encoded: String): ByteArray
        {
            return Ecoji.getDecoder().readFrom(encoded).writeToBytes()
        }
    }
}