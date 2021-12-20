package com.adrianishmael.zsyecoin

import java.security.interfaces.ECPublicKey

class Addresses {
    companion object{
        fun fromPublicKey(publicKey: ECPublicKey): ByteArray {
            val publicKeyHash: ByteArray = Hashing.hash(publicKey.encoded)
            return Constants.AddressHeader + Constants.AddressVersionPublicKey + publicKeyHash + getChecksumFromHash(publicKeyHash)
        }

        fun decodeAddress(address: ByteArray): DecodedAddress {
            val decodedAddress = DecodedAddress()
            val header = ByteArray(Constants.AddressHeader.size)
            decodedAddress.version = ByteArray(Constants.AddressVersionPublicKey.size)
            decodedAddress.checksum = ByteArray(Constants.AddressChecksumBytes)
            decodedAddress.hash = ByteArray(Constants.HashSize)
            System.arraycopy(address, 0, header, 0, Constants.AddressHeader.size)
            System.arraycopy(address, Constants.AddressHeader.size, decodedAddress.version, 0, Constants.AddressVersionPublicKey.size)
            System.arraycopy(address, Constants.AddressHeader.size + Constants.AddressVersionPublicKey.size, decodedAddress.hash, 0, Constants.HashSize)
            System.arraycopy(address, address.size - Constants.AddressChecksumBytes, decodedAddress.checksum, 0, Constants.AddressChecksumBytes)
            return decodedAddress
        }

        fun getChecksumFromHash(publicKeyHash: ByteArray): ByteArray {
            val checksum = ByteArray(Constants.AddressChecksumBytes)
            System.arraycopy(Hashing.hash(publicKeyHash), 0, checksum, 0, Constants.AddressChecksumBytes)
            return checksum
        }

        fun verifyAddressChecksum(address: ByteArray): Boolean {
            val decodedAddress = decodeAddress(address)
            val expectedChecksum = getChecksumFromHash(decodedAddress.hash!!)
            for (i in expectedChecksum.indices) {
                if (expectedChecksum[i] != decodedAddress.checksum!![i]) return false
            }
            return true
        }

        class DecodedAddress {
            var version: ByteArray? = null
            var hash: ByteArray? = null
            var checksum: ByteArray? = null
        }
    }
}