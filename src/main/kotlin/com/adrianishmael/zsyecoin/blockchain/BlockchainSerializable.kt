package com.adrianishmael.zsyecoin.blockchain

import com.adrianishmael.zsyecoin.Hashing

interface BlockchainSerializable {
    fun serialize(): ByteArray
    fun deserialize(data: ByteArray)
    fun hash(): ByteArray {
        return Hashing.hash(serialize())
    }
}