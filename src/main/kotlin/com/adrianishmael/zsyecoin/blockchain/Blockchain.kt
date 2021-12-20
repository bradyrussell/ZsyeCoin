package com.adrianishmael.zsyecoin.blockchain

interface Blockchain {
    // look up a block by its hash
    fun getBlock(hash: ByteArray): Block

    // look up a block by its height, where 0 is the genesis block, the next block is 1, etc.
    fun getBlock(height: Int): Block

    // lookup a transaction by its hash
    fun getTransaction(hash: ByteArray): Transaction

    // get the current block height, can be thought of as the last index in the blockchain, 0 indexed
    fun getCurrentBlockHeight(): Int
}