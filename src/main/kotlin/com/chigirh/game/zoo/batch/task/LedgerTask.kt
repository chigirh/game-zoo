package com.chigirh.game.zoo.batch.task

import com.chigirh.game.zoo.common.system.SystemProfile
import com.chigirh.game.zoo.common.system.UserProfile
import com.chigirh.game.zoo.domain.repository.LedgerRepository
import org.springframework.stereotype.Component

@Component
class LedgerTask(
    override val systemProfile: SystemProfile,
    private val userProfile: UserProfile,
    private val ledgerRepository: LedgerRepository,
) : BatchTask(systemProfile) {
    override fun task() {
        while (userProfile.money.doesLedgerNotEmpty()) {
            userProfile.money.poolLedger()?.let { ledgerRepository.insert(it) }
        }
    }

    override fun interval() = 30000L
}