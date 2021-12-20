import com.adrianishmael.zsyecoin.Constants
import com.adrianishmael.zsyecoin.Encoding
import com.adrianishmael.zsyecoin.Hashing
import com.adrianishmael.zsyecoin.toHex
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.concurrent.ThreadLocalRandom

class TestHashing {
    @Test
    fun testHashDifficulty() {
        var hash1:ByteArray = ByteArray(32);
        do {
            ThreadLocalRandom.current().nextBytes(hash1);
            hash1 = Hashing.hash(hash1);
        } while (!Hashing.validateHash(hash1, 3))

        println(Encoding.encode(hash1))
        Assertions.assertEquals(Encoding.encode(hash1).substring(0,4), Encoding.encode(Constants.Prefix).substring(0,4))
    }

    @Test
    fun estimateMiningTime() {
        for (i in 0 until Hashing.hash("").size)
        {
            var startTime = Instant.now().epochSecond;
            var hash1 = ByteArray(32);
            do {
                ThreadLocalRandom.current().nextBytes(hash1);
                hash1 = Hashing.hash(hash1);
            } while (!Hashing.validateHash(hash1, i))
            var duration = Instant.now().epochSecond - startTime;
            println("Simulated finding a block at difficulty $i in $duration seconds. ")
        }
    }
}