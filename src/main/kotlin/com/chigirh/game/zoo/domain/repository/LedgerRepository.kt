package com.chigirh.game.zoo.domain.repository

import com.chigirh.game.zoo.domain.model.money.Ledger

/**
 * Ledger repository interface.
 */
interface LedgerRepository {
    fun insert(ledger: Ledger)
}