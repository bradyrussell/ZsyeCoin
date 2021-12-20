package com.adrianishmael.zsyecoin.blockchain

class Block : BlockchainSerializable, BlockchainVerifiable {
    val transactions = listOf<Transaction>()
    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun verify(): Boolean {
        TODO("Not yet implemented")
    }
}