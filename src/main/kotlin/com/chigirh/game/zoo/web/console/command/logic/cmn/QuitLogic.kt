package com.chigirh.game.zoo.web.console.command.logic.cmn

import com.chigirh.game.zoo.common.system.SystemProfile
import com.chigirh.game.zoo.web.console.command.CommandId
import com.chigirh.game.zoo.web.console.command.logic.LogicBase
import com.chigirh.game.zoo.web.console.io.Display
import org.springframework.stereotype.Component

@Component("l_cmn_0000")
class QuitLogic(
    private val systemProfile: SystemProfile,
) : LogicBase() {
    override fun logic(input: String) {
        Display("ゲームを終了します。")
        systemProfile.setQuit(true)
    }

    override fun getId() = CommandId.L_QUIT
}