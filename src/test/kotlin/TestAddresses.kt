import com.adrianishmael.zsyecoin.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.security.SecureRandom
import java.security.interfaces.ECPublicKey

class TestAddresses {
    @Test
    fun testGenerateAddress() {
        val keypair = Keys.makeKeyPair(SecureRandom.getSeed(32))
        val address = Addresses.fromPublicKey(keypair.public as ECPublicKey)
        println(Encoding.encode(address))
        println(Addresses.verifyAddressChecksum(address))
        Assertions.assertTrue(Addresses.verifyAddressChecksum(address))
    }

    @Test
    fun parseAddress() {
        val keypair = Keys.makeKeyPair(SecureRandom.getSeed(32))
        val address = Addresses.fromPublicKey(keypair.public as ECPublicKey)
        println(Encoding.encode(address))
        val decoded = Addresses.decodeAddress(address)
        println("Address Bytes: "+address.toHex() + " | " + Encoding.encode(address))
        println("Header: "+Constants.AddressHeader.toHex() + " | " + Encoding.encode(Constants.AddressHeader))
        println("Version: "+decoded.version!!.toHex() + " | " + Encoding.encode(decoded.version!!))
        println("Hash: "+decoded.hash!!.toHex() + " | " + Encoding.encode(decoded.hash!!))
        println("Checksum: "+decoded.checksum!!.toHex() + " | " + Encoding.encode(decoded.checksum!!))
    }
}