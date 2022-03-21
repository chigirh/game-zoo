package com.chigirh.game.zoo.domain.model.money

import java.time.LocalDateTime

data class Ledger(
    val timeStamp: LocalDateTime,
    val amount: Int
)
