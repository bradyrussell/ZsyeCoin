package com.adrianishmael.zsyecoin.script

// reference for valid emoji: https://github.com/abock/dotnet-ecoji/blob/master/src/Ecoji/Ecoji.Table.g.cs

enum class ZsyeOperator(val emoji:String) {
    NOP("💤"),
    TRUE("👍"),
    FALSE("👎"),
    ;

    companion object {
        fun fromEmoji(emoji: String): ZsyeOperator? {
            return values().firstOrNull { it.emoji == emoji }
        }

        fun fromText(text: String): ZsyeOperator? {
            return values().firstOrNull { it.name.equals(text, true) }
        }
    }
}