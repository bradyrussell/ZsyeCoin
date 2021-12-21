package com.adrianishmael.zsyecoin.blockchain.implementations

import com.adrianishmael.zsyecoin.toHex
import com.adrianishmael.zsyecoin.zsyeHash
import com.adrianishmael.zsyecoin.Constants.Companion.Charset
import com.adrianishmael.zsyecoin.blockchain.Block

import com.adrianishmael.zsyecoin.blockchain.Blockchain
import java.nio.file.Files
import java.io.IOException
import com.adrianishmael.zsyecoin.blockchain.Transaction
import java.nio.file.Path

class FlatFileBlockchain : Blockchain {
    private var databasePath: String? = null
    override fun getBlock(hash: ByteArray): Block? {
        var block: Block? = null
        try {
            val bytes = Files.readAllBytes(Path.of(databasePath!!, BlocksDatabase, hash.toHex()))
            block = Block(null)
            block.deserialize(bytes)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return block
    }

    override fun getBlock(height: Long): Block? {
        try {
            return getBlock(
                Files.readAllBytes(
                    Path.of(
                        databasePath!!,
                        HeightToHashDatabase,
                        java.lang.Long.toString(height)
                    )
                )
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    override fun getTransaction(hash: ByteArray): Transaction? {
        var transaction: Transaction? = null
        try {
            val bytes = Files.readAllBytes(Path.of(databasePath!!, BlocksDatabase, hash.toHex()))
            transaction = Transaction()
            transaction.deserialize(bytes)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return transaction
    }

    override fun getCurrentBlockHeight(): Long {
        val blockheight = getProperty("blockheight")
        return blockheight?.toLong() ?: -1
    }

    private fun createSubDirectories() {
        val databases = listOf(BlocksDatabase, TransactionsDatabase, HeightToHashDatabase, MetaDatabase)
        for (database in databases) {
            if(!Files.isDirectory(Path.of(databasePath!!, database))) {
                Files.createDirectory(Path.of(databasePath!!, database))
            }
        }
    }

    override fun open(database: String, createIfNotFound: Boolean): Boolean {
        if (Files.isDirectory(Path.of(database))) {
            databasePath = database
            createSubDirectories()
            return true
        } else {
            if (!Files.exists(Path.of(database)) && createIfNotFound) {
                try {
                    Files.createDirectory(Path.of(database))
                    createSubDirectories()
                    databasePath = database
                    return true
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return false
    }

    override fun close() {
        databasePath = null
    }

    override fun isOpen(): Boolean {
        return databasePath != null
    }

    override fun putBlock(block: Block) {
        try {
            Files.write(Path.of(databasePath!!, BlocksDatabase, block.hash().toHex()), block.serialize())
            for (transaction in block.transactions) {
                putTransaction(transaction)
            }
            putProperty("blockheight", block.header!!.height.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun putTransaction(transaction: Transaction) {
        try {
            Files.write(
                Path.of(databasePath!!, TransactionsDatabase, transaction.hash().toHex()),
                transaction.serialize()
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun getProperty(key: String): String? {
        try {
            return Files.readString(Path.of(databasePath!!, MetaDatabase, key.zsyeHash().toHex()), Charset)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    override fun putProperty(key: String, value: String) {
        try {
            Files.writeString(Path.of(databasePath!!, MetaDatabase, key.zsyeHash().toHex()), value, Charset)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun verify(): Boolean {
        for (i in 0..getCurrentBlockHeight()) {
            if (!getBlock(i)!!.verify()) {
                return false
            }
        }
        return true
    }

    companion object {
        const val BlocksDatabase = "block"
        const val TransactionsDatabase = "transaction"
        const val HeightToHashDatabase = "height"
        const val MetaDatabase = "meta"
    }
}