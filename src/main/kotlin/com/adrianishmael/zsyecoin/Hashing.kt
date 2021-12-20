package com.adrianishmael.zsyecoin

import java.security.MessageDigest

class Hashing {
    companion object {
        fun hash(input: ByteArray): ByteArray {
            return MessageDigest.getInstance(Constants.HashAlgorithm).digest(input)
        }

        fun hash(input: String): ByteArray {
            return hash(input.toByteArray(Constants.Charset))
        }

        // get the number of bytes (not encoded characters, pre-encoding bytes)
        // that match the prefix (infinitely repeated), starting from the beginning of the hash
        // for instance if the prefix is AB
        //      a hash of ABCDEF... would have a difficulty of 2,
        //      ABABCC... would have a difficulty of 4,
        //      BCABAB... would be 0, etc
        // this is used as an exponential difficulty adjustment, as it is an order of magnitude more difficult to
        // find a hash of difficulty n+1 compared to n
        fun getHashDifficulty(hash: ByteArray): Int {
            var n = 0

            for(i in hash.indices) {
                if(hash[i] != Constants.Prefix[i % Constants.Prefix.size]) return n
                n++
            }
            return n
        }

        // check whether a given hash satisfies a given difficulty requirement
        // this means the hash difficulty must be >= the given difficulty
        fun validateHash(hash: ByteArray, difficulty: Int): Boolean {
            for(i in 0 until difficulty) {
                if(hash[i] != Constants.Prefix[i % Constants.Prefix.size]) return false
            }
            return true
        }
    }
}