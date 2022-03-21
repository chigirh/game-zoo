package com.chigirh.game.zoo.web.console.command.page.cmn

import com.chigirh.game.zoo.common.system.SystemProfile
import com.chigirh.game.zoo.common.system.UserProfile
import com.chigirh.game.zoo.common.system.UserSettings
import com.chigirh.game.zoo.web.console.command.CommandId
import com.chigirh.game.zoo.web.console.command.CommandManager
import com.chigirh.game.zoo.web.console.command.page.PageBase
import org.springframework.stereotype.Component

@Component("p_cmn_0000")
class RootPage(
    override val systemProfile: SystemProfile,
    override val userProfile: UserProfile,
    override val userSettings: UserSettings,
    override val commandManager: CommandManager,
) : PageBase(systemProfile, userProfile, userSettings, commandManager) {
    override fun description() = "今日は何をしますか？"

    override fun getId() = CommandId.P_ROOT
}