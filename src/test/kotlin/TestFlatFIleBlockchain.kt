import com.adrianishmael.zsyecoin.Constants
import com.adrianishmael.zsyecoin.blockchain.*
import com.adrianishmael.zsyecoin.blockchain.implementations.FlatFileBlockchain
import com.adrianishmael.zsyecoin.zsyeIntLengthDecode
import com.adrianishmael.zsyecoin.zsyeIntLengthEncode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.concurrent.ThreadLocalRandom

class TestFlatFIleBlockchain {
    @Test
    fun testCreateOrOpenDatabase() {
        val db = FlatFileBlockchain()
        Assertions.assertTrue(db.open("blockchain", true))
        Assertions.assertTrue(db.isOpen())
        db.close()
        Assertions.assertFalse(db.isOpen())
    }

    @Test
    fun testDatabaseProperties() {
        val db = FlatFileBlockchain()
        Assertions.assertTrue(db.open("blockchain", true))
        var prop = db.getProperty("testDatabaseProperties")
        prop = if(prop == null) {
            "0"
        } else {
            (Integer.parseInt(prop)+1).toString()
        }
        db.putProperty("testDatabaseProperties", prop)
    }

    @Test
    fun testDatabaseTransaction() {
        val db = FlatFileBlockchain()
        Assertions.assertTrue(db.open("blockchain", true))

        val transaction = Transaction(listOf(Input(ByteArray(Constants.HashSize), 0, ByteArray(1023))), listOf(Output(1337, ByteArray(1022))))
        db.putTransaction(transaction)
    }

    @Test
    fun testIntLenEncoding() {
        val test = ByteArray(32)
        val test2 = ByteArray(32)
        ThreadLocalRandom.current().nextBytes(test)
        ThreadLocalRandom.current().nextBytes(test2)

        val encoded = test.zsyeIntLengthEncode() + test2.zsyeIntLengthEncode()
        val (decoded, next) = encoded.zsyeIntLengthDecode()
        val (decoded2, next2) = next.zsyeIntLengthDecode()

        Assertions.assertArrayEquals(decoded, test)
        Assertions.assertArrayEquals(decoded2, test2)
        Assertions.assertArrayEquals(next2, ByteArray(0))
    }
}