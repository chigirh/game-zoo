package com.chigirh.game.zoo.infra.memory.store

import com.chigirh.game.zoo.domain.model.money.Ledger
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class LedgerStore : Store<LedgerKey, Ledger>()

fun Ledger.key() = LedgerKey(this.timeStamp)

data class LedgerKey(
    val value: LocalDateTime
)