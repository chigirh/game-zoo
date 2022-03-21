package com.chigirh.game.zoo.web.console.command

/**
 * Console command interface.
 */
interface Command {
    operator fun invoke(input: String)
    fun getId(): CommandId
}