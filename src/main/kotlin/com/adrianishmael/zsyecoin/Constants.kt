package com.adrianishmael.zsyecoin

class Constants {
    companion object{
        val Charset = Charsets.UTF_8
        const val HashAlgorithm = "SHA3-256"
        val HashSize = Hashing.hash("").size
        val Prefix = Encoding.decode("💵💵😎😤")
        const val EllipticCurveName = "secp256k1"
        const val SignatureName = "SHA512withECDSA"
        val AddressHeader = Encoding.decode("💵💵💵💵")
        val AddressVersionPublicKey = Encoding.decode("😎😎😎😎")
        const val AddressChecksumBytes = 5
    }
}

