package com.adrianishmael.zsyecoin.blockchain

import com.adrianishmael.zsyecoin.*

class Output(var amount: Long?, var lockingScript: ByteArray?) : BlockchainSerializable, BlockchainVerifiable {
    // the amount of satoshis to send
    // the script to lock the output with

    override fun serialize(): ByteArray {
        return amount!!.zsyeBytes() + lockingScript!!.zsyeIntLengthEncode()
    }

    override fun deserialize(data: ByteArray) {
        val (dAmount, next) = data.zsyeFixedLengthDecode(Long.SIZE_BYTES)
        val (dLockingScript) = next.zsyeIntLengthDecode()

        amount = dAmount.zsyeLong()
        lockingScript = dLockingScript
    }

    override fun verify(): Boolean {
        TODO("Not yet implemented")
    }
}