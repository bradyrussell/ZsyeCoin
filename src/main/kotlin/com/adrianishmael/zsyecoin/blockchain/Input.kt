package com.adrianishmael.zsyecoin.blockchain

// an input spends an unspent transaction output that exists earlier in the blockchain
class Input(val spendingHash: ByteArray, val spendingIndex: Int, val unlockingScript: ByteArray) : BlockchainSerializable, BlockchainVerifiable {
    // the hash of the transaction that the output to spend exists on
    // the index of the output to spend on the transaction
    // the script required to unlock the output

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