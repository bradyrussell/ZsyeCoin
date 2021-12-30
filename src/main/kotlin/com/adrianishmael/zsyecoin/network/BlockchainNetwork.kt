package com.adrianishmael.zsyecoin.network

import com.adrianishmael.zsyecoin.Constants
import com.adrianishmael.zsyecoin.blockchain.Block
import com.adrianishmael.zsyecoin.blockchain.Transaction
import java.util.function.Consumer

interface BlockchainNetwork {
    // start up network connections
    fun start()

    // disconnect and clean up
    fun stop()

    // return the current network protocol version
    fun getVersion(): Byte {
        return Constants.NetworkProtocolVersion
    }

    // whether we are connected and ready to send blocks or transactions
    fun isReady(): Boolean

    // attempt to connect to a new peer
    fun addPeer(address: String)

    // get the number of connected peers
    fun getPeerCount(): Int {
        return getPeerList().size
    }

    // get the list of connected peers' addresses
    fun getPeerList(): List<String>

    // request historical blockchain data from peers.
    fun sync()

    // submit a new block to the network
    fun submitBlock(block: Block)

    // submit a new transaction to the network
    fun submitTransaction(transaction: Transaction)

    // optional: this will be called upon receiving a block from a peer if it is the first time we have seen that block.
    fun onReceiveBlock(blockConsumer: Consumer<Block>) {

    }

    // optional: this will be called upon receiving a transaction from a peer if it is the first time we have seen that transaction.
    fun onReceiveTransaction(transactionConsumer: Consumer<Transaction>) {

    }

    // this will be called before connecting to a peer, returning false will prevent the connection
    fun isPeerConnectionAllowed(peer: String): Boolean {
        return true;
    }
}