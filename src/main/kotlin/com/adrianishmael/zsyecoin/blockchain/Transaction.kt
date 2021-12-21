package com.adrianishmael.zsyecoin.blockchain

class Transaction(val inputs: List<Input> = listOf(), val outputs: List<Output> = listOf()) : BlockchainSerializable, BlockchainVerifiable {
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