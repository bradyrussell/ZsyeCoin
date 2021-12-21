package com.adrianishmael.zsyecoin.blockchain

import com.adrianishmael.zsyecoin.*

// an input spends an unspent transaction output that exists earlier in the blockchain
class Input(var spendingHash: ByteArray?, var spendingIndex: Int?, var unlockingScript: ByteArray?) : BlockchainSerializable, BlockchainVerifiable {
    // the hash of the transaction that the output to spend exists on
    // the index of the output to spend on the transaction
    // the script required to unlock the output

    override fun serialize(): ByteArray {
        return spendingHash!! + spendingIndex!!.zsyeBytes() + unlockingScript!!.zsyeIntLengthEncode()
    }

    override fun deserialize(data: ByteArray) {
        val (dSpendingHash, next) = data.zsyeFixedLengthDecode(Constants.HashSize)
        val (dSpendingIndex, next1) = next.zsyeFixedLengthDecode(Int.SIZE_BYTES)
        val (dUnlockingScript) = next1.zsyeIntLengthDecode()

        spendingHash = dSpendingHash
        spendingIndex = dSpendingIndex.zsyeInt()
        unlockingScript = dUnlockingScript
    }

    override fun verify(): Boolean {
        TODO("Not yet implemented")
    }

}