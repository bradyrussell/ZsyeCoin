package com.adrianishmael.zsyecoin

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream

fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }

fun ByteArray.zsyeEncode(): String { return Encoding.encode(this) }
fun String.zsyeDecode(): ByteArray { return Encoding.decode(this) }

fun ByteArray.zsyeHash(): ByteArray { return Hashing.hash(this) }
fun String.zsyeHash(): ByteArray { return Hashing.hash(this) }

fun ByteArray.zsyeIntLengthEncode(): ByteArray { return this.size.zsyeBytes() + this }
fun ByteArray.zsyeIntLengthDecode(): Pair<ByteArray, ByteArray> {
    val len = this.take(4).toByteArray().zsyeInt()
    return Pair(this.copyOfRange(4, 4 + len), this.takeLast(this.size - (4 + len)).toByteArray())
}

fun ByteArray.zsyeFixedLengthDecode(length: Int): Pair<ByteArray, ByteArray> {
    return Pair(this.copyOfRange(0, length), this.takeLast(this.size - length).toByteArray())
}

fun Int.zsyeBytes(): ByteArray {
    val baos = ByteArrayOutputStream()
    val data = DataOutputStream(baos)
    data.writeInt(this)
    return baos.toByteArray()
}

fun ByteArray.zsyeInt(): Int {
    val bais = ByteArrayInputStream(this)
    val data = DataInputStream(bais)
    return data.readInt()
}

fun Long.zsyeBytes(): ByteArray {
    val baos = ByteArrayOutputStream()
    val data = DataOutputStream(baos)
    data.writeLong(this)
    return baos.toByteArray()
}

fun ByteArray.zsyeLong(): Long {
    val bais = ByteArrayInputStream(this)
    val data = DataInputStream(bais)
    return data.readLong()
}