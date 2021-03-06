package com.adrianishmael.zsyecoin.blockchain

interface Blockchain : BlockchainVerifiable {
    fun open(database: String, createIfNotFound: Boolean): Boolean
    fun close()

    fun isOpen(): Boolean

    // look up a block by its hash
    fun getBlock(hash: ByteArray): Block?
    fun putBlock(block: Block)

    // look up a block by its height, where 0 is the genesis block, the next block is 1, etc.
    fun getBlock(height: Long): Block?

    // lookup a transaction by its hash
    fun getTransaction(hash: ByteArray): Transaction?
    fun putTransaction(transaction: Transaction)

    // get the current block height, can be thought of as the last index in the blockchain, 0 indexed
    fun getCurrentBlockHeight(): Long

    fun getProperty(key: String): String?
    fun putProperty(key: String, value: String)
}