package com.adrianishmael.zsyecoin.blockchain

class Output : BlockchainSerializable {
    // the amount of satoshis to send
    val amount: Long? = null;
    // the script to lock the output with
    val lockingScript: ByteArray? = null;

    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}