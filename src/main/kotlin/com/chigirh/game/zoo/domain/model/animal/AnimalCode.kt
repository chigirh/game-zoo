package com.chigirh.game.zoo.domain.model.animal

enum class AnimalCode(
    private val value: String,
) {
    DOG("A_01"),
    CAT("A_02"),
    HORSE("A_03"),
    GIRAFFE("A_04"),
    LION("A_05"),
    ;

    fun raw() = value

    companion object {
        fun of(v: String) = values().find { it.value == v } ?: throw IllegalArgumentException("AnimalCode value:$v")
    }
}
