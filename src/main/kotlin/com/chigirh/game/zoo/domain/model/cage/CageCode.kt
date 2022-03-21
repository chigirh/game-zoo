package com.chigirh.game.zoo.domain.model.cage

enum class CageCode(
    private val value: String,
) {
    NORMAL("C_01"),
    SMALL("C_02"),
    LARGE("C_03"),
    STRONG("C_04"),
    ;

    fun raw() = value

    companion object {
        fun of(v: String): CageCode =
            values().find { it.value == v } ?: throw IllegalArgumentException("CageCode value:$v")
    }
}