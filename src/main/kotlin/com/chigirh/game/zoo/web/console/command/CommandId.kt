package com.chigirh.game.zoo.web.console.command

import com.chigirh.game.zoo.domain.model.cage.CageCode

enum class CommandId(
    private val value: String,
) {
    P_ROOT("p_cmn_0000"),

    P_SHOP_ROOT("p_shop_0001"),
    P_SHOP_CAGE_BUY("p_shop_0002"),
    P_SHOP_ANIMAL_BUY("p_shop_0003"),



    L_QUIT("l_cmn_0000"),

    L_SHOP_CAGE_BUY("l_shop_0001"),
    L_SHOP_ANIMAL_BUY("l_shop_0002"),
    ;

    fun raw() = value

    fun of(v: String): CageCode =
        CageCode.values().find { value == v } ?: throw IllegalArgumentException("CommandId value:$v")
}