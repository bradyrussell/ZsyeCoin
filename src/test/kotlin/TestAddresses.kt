import com.adrianishmael.zsyecoin.Addresses
import com.adrianishmael.zsyecoin.Encoding
import com.adrianishmael.zsyecoin.Keys
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
}