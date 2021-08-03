package com.adrianishmael.zsyecoin

import io.github.netvl.ecoji.Ecoji
import java.io.File

fun main() {
    val encoded = Ecoji.getEncoder().readFrom("ZsyeCoin").writeToString()
    val decoded = Ecoji.getDecoder().readFrom(encoded).writeToString()

    File("emoji.txt").writeText(encoded)
    println("Encoded:$encoded")
    println("Decoded: $decoded")
    println("Hello Zsye!")
}