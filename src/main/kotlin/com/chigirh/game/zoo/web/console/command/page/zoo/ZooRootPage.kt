package com.chigirh.game.zoo.web.console.command.page.zoo

import com.chigirh.game.zoo.common.system.SystemProfile
import com.chigirh.game.zoo.common.system.UserProfile
import com.chigirh.game.zoo.common.system.UserSettings
import com.chigirh.game.zoo.web.console.command.CommandId
import com.chigirh.game.zoo.web.console.command.CommandManager
import com.chigirh.game.zoo.web.console.command.page.PageBase
import org.springframework.stereotype.Component

@Component("p_zoo_0001")
class ZooRootPage(
    override val systemProfile: SystemProfile,
    override val userProfile: UserProfile,
    override val userSettings: UserSettings,
    override val commandManager: CommandManager,
) : PageBase(systemProfile, userProfile, userSettings, commandManager) {
    override fun description(input: String) = "何をしますか?"

    override fun getId() = CommandId.P_ZOO_ROOT
}