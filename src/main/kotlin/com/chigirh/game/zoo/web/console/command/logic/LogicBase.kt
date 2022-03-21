package com.chigirh.game.zoo.web.console.command.logic

import com.chigirh.game.zoo.web.console.command.Command
import com.chigirh.game.zoo.web.console.io.Display

/**
 * Console logic base class.
 *
 * this is contents of "Composite"
 */
abstract class LogicBase : Command {
    override fun invoke(input: String) {
        Display("")
        logic(input)
    }

    protected abstract fun logic(input: String)
}