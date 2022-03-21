package com.chigirh.game.zoo.batch.task

import com.chigirh.game.zoo.common.system.SystemProfile
import kotlinx.coroutines.delay

abstract class BatchTask(
    protected open val systemProfile: SystemProfile,
) {
    suspend operator fun invoke() {
        while (!systemProfile.istQuit()) {
            delay(interval())
            task()
        }
    }

    protected abstract fun task()

    protected abstract fun interval(): Long
}