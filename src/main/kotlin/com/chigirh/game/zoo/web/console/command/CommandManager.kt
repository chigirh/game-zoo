package com.chigirh.game.zoo.web.console.command

import com.chigirh.game.zoo.common.context.ApplicationContextHolder
import org.springframework.stereotype.Component

@Component
class CommandManager(
    private val factory: ChoiceFactory,
) {
    private val map = HashMap<CommandId, List<Choice>>()

    fun getChoiceBox(commandId: CommandId): List<Choice> {
        if (!map.containsKey(commandId)) {
            map[commandId] = createChoiceBox(commandId)
        }
        return map[commandId]!!
    }

    private fun createChoiceBox(commandId: CommandId) = when (commandId) {
        CommandId.P_ROOT -> {
            listOf(
                factory(1, "ショップへ行く", CommandId.P_SHOP_ROOT),
                factory(2, "q", "quit", CommandId.L_QUIT),
            )
        }

        CommandId.P_SHOP_ROOT -> {
            listOf(
                factory(1, "檻を買う", CommandId.P_SHOP_CAGE_BUY),
                factory(2, "動物を買う", CommandId.P_SHOP_ANIMAL_BUY),
            )
        }
        else -> throw IllegalArgumentException()
    }
}

@Component
class ChoiceFactory {
    operator fun invoke(
        index: Int,
        label: String,
        commandId: CommandId
    ) = invoke(
        index = index,
        value = "$index",
        label = label,
        commandId = commandId
    )

    operator fun invoke(
        index: Int,
        value: String,
        label: String,
        commandId: CommandId
    ) = Choice(
        index = index,
        value = value,
        label = label,
        command = ApplicationContextHolder.getCommand(commandId)
    )
}

data class Choice(
    val index: Int,
    val value: String,
    val label: String,
    val command: Command,
) {
    fun show() = "$value -> $label"
}
