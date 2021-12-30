package com.adrianishmael.zsyecoin.script

class ZsyeCode {
    companion object {
/*        fun fromText(text: String, delimiter: String): String {
            return text.split(delimiter).map { ZsyeOperator.fromText(it.trim()) ?: ZsyeOperator.NOP }.joinToString("") { it.emoji }
        }

        fun toText(zsyecode: String, delimiter: String): String {
            return zsyecode.toCharArray().toList().chunked(2).map { ZsyeOperator.fromEmoji(it[0].toString() + it[1].toString()) ?: ZsyeOperator.NOP }.joinToString(delimiter) { it.name }
        }*/

        fun fromText(text: String, delimiter: String): List<ZsyeOperator> {
            return text.split(delimiter).map { ZsyeOperator.fromText(it.trim()) ?: ZsyeOperator.NOP }
        }

        fun toText(zsyecode: List<ZsyeOperator>, delimiter: String): String {
            return zsyecode.joinToString(delimiter) { it.name }
        }

        fun fromZsye(zsyecode: String): List<ZsyeOperator> {
            return zsyecode.toCharArray().toList().chunked(2).map { ZsyeOperator.fromEmoji(it[0].toString() + it[1].toString()) ?: ZsyeOperator.NOP }
        }

        fun toZsye(zsyecode: List<ZsyeOperator>): String {
            return zsyecode.joinToString("") { it.emoji }
        }
    }
}