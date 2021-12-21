package com.adrianishmael.zsyecoin.blockchain

class BlockHeader(val version: Long, val previousBlockHash: ByteArray, val merkleRootHash: ByteArray, val timestamp: Long, val difficulty: Long, val height: Long, val nonce: Long) : BlockchainSerializable, BlockchainVerifiable {
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