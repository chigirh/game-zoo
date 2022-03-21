package com.chigirh.game.zoo.common.system

import org.springframework.stereotype.Component

@Component
class SystemProfile {
    private var isQuit: Boolean = false

    @Synchronized
    fun setQuit(isQuit: Boolean) {
        this.isQuit = isQuit
    }

    fun istQuit() = isQuit
}