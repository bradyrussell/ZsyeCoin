package com.adrianishmael.zsyecoin.blockchain

import com.adrianishmael.zsyecoin.zsyeIntLengthDecode
import com.adrianishmael.zsyecoin.zsyeIntLengthEncode

class Transaction(var inputs: List<Input> = listOf(), var outputs: List<Output> = listOf()) : BlockchainSerializable, BlockchainVerifiable {
    override fun serialize(): ByteArray {
        var inputs = ByteArray(0)
        for (bytes in this.inputs.stream().map { x -> x.serialize().zsyeIntLengthEncode() }) {
            inputs += bytes
        }
        var outputs = ByteArray(0)
        for (bytes in this.outputs.stream().map { x -> x.serialize().zsyeIntLengthEncode() }) {
            outputs += bytes
        }
        return inputs.zsyeIntLengthEncode() + outputs.zsyeIntLengthEncode()
    }

    override fun deserialize(data: ByteArray) {
        val (dInputs, next) = data.zsyeIntLengthDecode()
        val (dOutputs) = next.zsyeIntLengthDecode()

        var dInNext = dInputs
        while(dInNext.isNotEmpty()) {
            val (iInput, iNext) = dInNext.zsyeIntLengthDecode()
            val input = Input(null,null,null)
            input.deserialize(iInput)
            inputs = inputs + input
            dInNext = iNext
        }

        var dOutNext = dOutputs
        while(dOutNext.isNotEmpty()) {
            val (oOutput, oNext) = dOutNext.zsyeIntLengthDecode()
            val output = Output(null, null)
            output.deserialize(oOutput)
            outputs = outputs + output
            dOutNext = oNext
        }
    }

    override fun verify(): Boolean {
        TODO("Not yet implemented")
    }
}