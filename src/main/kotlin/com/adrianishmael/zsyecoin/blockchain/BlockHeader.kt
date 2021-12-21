package com.adrianishmael.zsyecoin.blockchain

class BlockHeader : BlockchainSerializable, BlockchainVerifiable {
    val version = null
    val previousBlockHash = null
    val merkleRootHash = null
    val timestamp = null
    val difficulty = null
    val height = null
    val nonce = null
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