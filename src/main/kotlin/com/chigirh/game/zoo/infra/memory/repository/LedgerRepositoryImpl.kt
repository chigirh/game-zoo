package com.chigirh.game.zoo.infra.memory.repository

import com.chigirh.game.zoo.domain.model.money.Ledger
import com.chigirh.game.zoo.domain.repository.LedgerRepository
import com.chigirh.game.zoo.infra.memory.store.LedgerStore
import com.chigirh.game.zoo.infra.memory.store.key
import org.springframework.stereotype.Repository

/**
 * Ledger repository impl.
 */
@Repository
class LedgerRepositoryImpl(
    private val store: LedgerStore
) : LedgerRepository {

    override fun insert(ledger: Ledger) {
        store[ledger.key()] = ledger
    }
}