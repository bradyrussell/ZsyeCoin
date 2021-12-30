import com.adrianishmael.zsyecoin.*
import com.adrianishmael.zsyecoin.script.ZsyeCode
import com.adrianishmael.zsyecoin.script.ZsyeOperator
import io.github.netvl.ecoji.Ecoji
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.lang.Exception
import java.security.SecureRandom
import java.security.interfaces.ECPublicKey
import java.util.concurrent.ThreadLocalRandom

class TestZsyeCode {

    @Test
    fun testZsyeOperatorsValidEncoding() {
        for (value in ZsyeOperator.values()) {
            println(value.emoji)
            Assertions.assertDoesNotThrow { Encoding.decode(value.emoji + value.emoji + value.emoji + value.emoji) }
        }
    }

    @Test
    fun testZsyeCodeToFromText() {
        val text = "NOP NOP TRUE FALSE NOP INVALID TRUE FALSE"
        val zsyecode = ZsyeCode.toZsye(ZsyeCode.fromText(text," "))
        println(zsyecode)
        val text2 = ZsyeCode.toText(ZsyeCode.fromZsye(zsyecode), " ")
        println(text2)
        Assertions.assertEquals(ZsyeCode.fromText(text, " "), ZsyeCode.fromText(text2, " "))
    }
}