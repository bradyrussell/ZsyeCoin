package com.adrianishmael.zsyecoin.blockchain

// an input spends an unspent transaction output that exists earlier in the blockchain
class Input : BlockchainSerializable, BlockchainVerifiable {
    // the hash of the transaction that the output to spend exists on
    val spendingHash: ByteArray? = null
    // the index of the output to spend on the transaction
    val spendingIndex: Int? = null
    // the script required to unlock the output
    val unlockingScript: ByteArray? = null;

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