package com.adrianishmael.zsyecoin.blockchain

class Block(var header: BlockHeader?, var transactions: List<Transaction> = listOf()) : BlockchainSerializable, BlockchainVerifiable {
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