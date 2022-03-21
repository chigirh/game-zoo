package com.chigirh.game.zoo.web.console.command.page.zoo

import com.chigirh.game.zoo.common.system.SystemProfile
import com.chigirh.game.zoo.common.system.UserProfile
import com.chigirh.game.zoo.common.system.UserSettings
import com.chigirh.game.zoo.domain.model.cage.Cage
import com.chigirh.game.zoo.domain.usecase.cage.FetchCageUseCase
import com.chigirh.game.zoo.domain.usecase.cage.FetchCageUseCase.InputImpl
import com.chigirh.game.zoo.web.console.command.Choice
import com.chigirh.game.zoo.web.console.command.ChoiceFactory
import com.chigirh.game.zoo.web.console.command.CommandId
import com.chigirh.game.zoo.web.console.command.CommandManager
import com.chigirh.game.zoo.web.console.command.page.PageBase
import org.springframework.stereotype.Component

@Component("p_zoo_0002")
class PutAnimalRootPage(
    override val systemProfile: SystemProfile,
    override val userProfile: UserProfile,
    override val userSettings: UserSettings,
    override val commandManager: CommandManager,
    private val fetchCageUseCase: FetchCageUseCase,
    private val factory: ChoiceFactory,
) : PageBase(systemProfile, userProfile, userSettings, commandManager) {
    override fun description(input: String) = "どの檻に動物を入れますか？"

    override fun getChoiceBox(input: String): List<Choice> {
        val cages = fetchCageUseCase(InputImpl(cageId = null)).models
        return cages.mapIndexed(this::createChoice).toList()
    }

    private fun createChoice(idx: Int, cage: Cage) = factory(
        index = idx,
        value = "${cage.id}",
        label = "id:${cage.id},name:${cage.name},檻に入れられる動物数:${cage.getStorableSize()},檻に入れられる重量:${cage.getStorableWeight()}",
        commandId = CommandId.P_ZOO_ANIMAL_SELECT,
    )

    override fun getId() = CommandId.P_ZOO_PUT_ROOT
}