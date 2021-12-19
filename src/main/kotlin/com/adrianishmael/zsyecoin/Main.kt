package com.adrianishmael.zsyecoin

import io.github.netvl.ecoji.Ecoji

fun main() {
    val encode = Ecoji.getEncoder().readFrom("Hello Zsye").writeToString()
    println(encode)

    val hash = getSHA3Bytes(encode)
    println(Ecoji.getEncoder().readFrom(hash).writeToString())

    val difficulty = getHashDifficulty(hash)
    println(difficulty)

    val validate = validateHash(hash, difficulty)
    println(validate)
}
