package com.adrianishmael.zsyecoin

import java.math.BigInteger
import java.security.*
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.security.spec.*

class Keys {
    companion object{
        @Throws(NoSuchAlgorithmException::class, InvalidAlgorithmParameterException::class)
        fun makeKeyPair(seed: ByteArray): KeyPair {
            val keyGen = KeyPairGenerator.getInstance("EC")
            val ecSpec = ECGenParameterSpec(Constants.EllipticCurveName)
            keyGen.initialize(ecSpec, SecureRandom(seed))
            return keyGen.generateKeyPair()
        }

        @Throws(NoSuchAlgorithmException::class, InvalidKeyException::class, SignatureException::class)
        fun signData(keys: KeyPair, message: ByteArray): SignedData {
            val ecdsaSign: Signature = Signature.getInstance(Constants.SignatureName)
            ecdsaSign.initSign(keys.private)
            ecdsaSign.update(message)
            val signature: ByteArray = ecdsaSign.sign()
            return SignedData(keys.public.encoded, signature, message)
        }

        @Throws(
            NoSuchAlgorithmException::class,
            InvalidKeySpecException::class,
            InvalidKeyException::class,
            SignatureException::class
        )
        fun verifySignedData(message: SignedData): Boolean {
            val ecdsaVerify: Signature = Signature.getInstance(Constants.SignatureName)
            val keyFactory = KeyFactory.getInstance("EC")
            val publicKey = keyFactory.generatePublic(X509EncodedKeySpec(message.publicKey))
            ecdsaVerify.initVerify(publicKey)
            ecdsaVerify.update(message.message)
            return ecdsaVerify.verify(message.signature)
        }

        @Throws(
            NoSuchAlgorithmException::class,
            InvalidKeySpecException::class,
            InvalidKeyException::class,
            SignatureException::class
        )
        fun loadKeys(public: ByteArray, private: ByteArray): KeyPair {
            val keyFactory = KeyFactory.getInstance("EC")
            val publicKey = keyFactory.generatePublic(X509EncodedKeySpec(public))
            val privateKey = keyFactory.generatePrivate(PKCS8EncodedKeySpec(private))
            return KeyPair(publicKey, privateKey)
        }

        @Throws(GeneralSecurityException::class)
        fun getPublicKeyFromPrivateKey(privateKey: ECPrivateKey): ECPublicKey {
            return getPublicKey(privateKey)
        }

        class SignedData(val publicKey: ByteArray, val signature: ByteArray, val message: ByteArray)

        //https://stackoverflow.com/questions/19673962/codes-to-generate-a-public-key-in-an-elliptic-curve-algorithm-using-a-given-priv
        ///////////////////////////////////////////////////////////////
        private fun doublePoint(p: BigInteger, a: BigInteger, R: ECPoint): ECPoint {
            val result: ECPoint
            if (R == ECPoint.POINT_INFINITY) {
                result = R
            } else {
                var slope = R.affineX.pow(2).multiply(BigInteger.valueOf(3))
                slope = slope.add(a)
                slope = slope.multiply(R.affineY.multiply(BigInteger.TWO).modInverse(p))
                val Xout = slope.pow(2).subtract(R.affineX.multiply(BigInteger.TWO)).mod(p)
                val Yout = R.affineY.negate().add(slope.multiply(R.affineX.subtract(Xout))).mod(p)
                result = ECPoint(Xout, Yout)
            }
            return result
        }

        private fun addPoint(p: BigInteger, a: BigInteger, r: ECPoint, g: ECPoint): ECPoint {
            val result: ECPoint
            if (r == ECPoint.POINT_INFINITY) {
                result = g
            } else if (g == ECPoint.POINT_INFINITY) {
                result = r
            } else if (r === g || r == g) {
                result = doublePoint(p, a, r)
            } else {
                val gX = g.affineX
                val sY = g.affineY
                val rX = r.affineX
                val rY = r.affineY
                val slope = rY.subtract(sY).multiply(rX.subtract(gX).modInverse(p)).mod(p)
                val Xout = slope.modPow(BigInteger.TWO, p).subtract(rX).subtract(gX).mod(p)
                var Yout = sY.negate().mod(p)
                Yout = Yout.add(slope.multiply(gX.subtract(Xout))).mod(p)
                result = ECPoint(Xout, Yout)
            }
            return result
        }

        private fun scalmult(curve: EllipticCurve, g: ECPoint, kin: BigInteger): ECPoint {
            val field = curve.field
            if (field !is ECFieldFp) throw UnsupportedOperationException(field.javaClass.canonicalName)
            val p = field.p
            val a = curve.a
            var R = ECPoint.POINT_INFINITY
            var k = kin.mod(p)
            val length = k.bitLength()
            val binarray = ByteArray(length)
            for (i in 0 until length) {
                binarray[i] = k.mod(BigInteger.TWO).toByte()
                k = k.shiftRight(1)
            }
            for (i in length - 1 downTo 0) {
                R = doublePoint(p, a, R)
                if (binarray[i].toInt() == 1) R = addPoint(p, a, R, g)
            }
            return R
        }

        @Throws(GeneralSecurityException::class)
        private fun getPublicKey(pk: ECPrivateKey): ECPublicKey {
            val params = pk.params
            val w = scalmult(params.curve, pk.params.generator, pk.s)
            val kg = KeyFactory.getInstance("EC")
            return kg.generatePublic(ECPublicKeySpec(w, params)) as ECPublicKey
        }
    }
}