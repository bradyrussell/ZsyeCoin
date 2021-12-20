package com.adrianishmael.zsyecoin.blockchain

import java.util.function.Consumer

interface BlockchainNetwork {
    fun start()
    fun stop()

    fun isReady(): Boolean

    fun addPeer(address: String)
    fun getPeerCount(): Int

    fun submitBlock(block: Block)
    fun submitTransaction(transaction: Transaction)

    fun onReceiveBlock(blockConsumer: Consumer<Block>)
    fun onReceiveTransaction(transactionConsumer: Consumer<Transaction>)
}