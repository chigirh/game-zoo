package com.chigirh.game.zoo.web.console.command.page

import com.chigirh.game.zoo.common.system.SystemProfile
import com.chigirh.game.zoo.common.system.UserProfile
import com.chigirh.game.zoo.common.system.UserSettings
import com.chigirh.game.zoo.web.console.command.Command
import com.chigirh.game.zoo.web.console.command.CommandManager
import com.chigirh.game.zoo.web.console.io.Display
import com.chigirh.game.zoo.web.console.io.Entry
import org.apache.commons.lang3.StringUtils

/**
 * Console page base class.
 *
 * this is container of "Composite"
 */
abstract class PageBase(
    protected open val systemProfile: SystemProfile,
    protected open val userProfile: UserProfile,
    protected open val userSettings: UserSettings,
    protected open val commandManager: CommandManager,
) : Command {

    private var isRunning = false

    override operator fun invoke(input: String) {
        isRunning = true
        while (isRunning) {
            if (systemProfile.istQuit()) break

            Display("")
            // show header,description
            Display(header(input))
            Display(description(input))

            val choiceBox = getChoiceBox(input)

            // selection display.
            choiceBox.sortedBy { it.index }.forEach { Display(it.show()) }
            Display("$RETURN_VALUE -> $RETURN_LABEL")

            // user input.
            val entryValue = Entry()

            // get select command.
            runCatching {
                choiceBox.first { StringUtils.equals(it.value, entryValue) }
            }.fold(
                onSuccess = { it.command(entryValue) }, // command invoke.
                onFailure = { if (it is NoSuchElementException) entryFailure(entryValue) }
            )
        }
    }

    protected open fun header(input: String) = ("user name:${userProfile.userName},money:${userProfile.money()}z")

    abstract fun description(input: String): String

    private fun entryFailure(entryValue: String?) {
        if (StringUtils.equals(entryValue, RETURN_VALUE)) {
            isRunning = false
        } else Display("bad entry for $entryValue")
    }

    protected open fun getChoiceBox(input: String) = commandManager.getChoiceBox(getId())

    companion object {
        const val RETURN_VALUE = "r"
        const val RETURN_LABEL = "return"
    }
}