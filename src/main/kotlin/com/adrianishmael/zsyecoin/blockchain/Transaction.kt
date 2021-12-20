package com.adrianishmael.zsyecoin.blockchain

class Transaction : BlockchainSerializable {
    val inputs = listOf<Input>()
    val outputs = listOf<Output>()
    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}