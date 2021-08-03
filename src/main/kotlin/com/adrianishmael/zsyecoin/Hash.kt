package com.adrianishmael.zsyecoin

import java.security.MessageDigest

val prefix = arrayOf(0x50, 0x84.toByte(), 0x94.toByte(), 0x21, 0x25, 0x08, 0x49, 0x42, 0x12)

fun getSHA3String(input: String): String {
        return MessageDigest.getInstance("SHA3-256").digest(input.toByteArray()).toString()
}

fun getSHA3Bytes(input: String): ByteArray {
        return MessageDigest.getInstance("SHA3-256").digest(input.toByteArray())
}

fun getHashDifficulty(hash: ByteArray): Int {
        var n = 0

        for(i in 0..hash.size) {
                if(hash[i] != prefix[i % prefix.size]) return n
                n++
        }
        return n
}

fun validateHash(hash: ByteArray, difficulty: Int): Boolean {
        for(i in 0..difficulty) {
                if(hash[i] != prefix[i % prefix.size]) return false
        }
        return true
}
