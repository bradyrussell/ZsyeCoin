package com.adrianishmael.zsyecoin.blockchain

class Block : BlockchainSerializable {
    val transactions = listOf<Transaction>()
    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}