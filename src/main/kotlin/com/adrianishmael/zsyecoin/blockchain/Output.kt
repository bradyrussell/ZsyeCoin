package com.adrianishmael.zsyecoin.blockchain

class Output(val amount: Long, val lockingScript: ByteArray) : BlockchainSerializable, BlockchainVerifiable {
    // the amount of satoshis to send
    // the script to lock the output with

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