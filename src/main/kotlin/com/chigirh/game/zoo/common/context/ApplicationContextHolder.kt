package com.chigirh.game.zoo.common.context

import com.chigirh.game.zoo.web.console.command.Command
import com.chigirh.game.zoo.web.console.command.CommandId
import org.springframework.context.ApplicationContext

object ApplicationContextHolder {
    lateinit var application: ApplicationContext

    fun getBean(name: String) = application.getBean(name)

    fun getCommand(commandId: CommandId) =
        application.getBean(commandId.raw()).let { if (it is Command) it else throw IllegalArgumentException() }
}