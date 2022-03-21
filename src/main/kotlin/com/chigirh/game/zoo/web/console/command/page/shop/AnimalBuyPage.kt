package com.chigirh.game.zoo.web.console.command.page.shop

import com.chigirh.game.zoo.common.system.SystemProfile
import com.chigirh.game.zoo.common.system.UserProfile
import com.chigirh.game.zoo.common.system.UserSettings
import com.chigirh.game.zoo.domain.model.animal.AnimalCatalog
import com.chigirh.game.zoo.domain.usecase.animal.FetchAnimalCatalogUseCase
import com.chigirh.game.zoo.domain.usecase.animal.FetchAnimalCatalogUseCase.InputImpl
import com.chigirh.game.zoo.web.console.command.Choice
import com.chigirh.game.zoo.web.console.command.ChoiceFactory
import com.chigirh.game.zoo.web.console.command.CommandId
import com.chigirh.game.zoo.web.console.command.CommandManager
import com.chigirh.game.zoo.web.console.command.page.PageBase
import org.springframework.stereotype.Component

@Component("p_shop_0003")
class AnimalBuyPage(
    override val systemProfile: SystemProfile,
    override val userProfile: UserProfile,
    override val userSettings: UserSettings,
    override val commandManager: CommandManager,
    private val fetchAnimalCatalogUseCase: FetchAnimalCatalogUseCase,
    private val factory: ChoiceFactory,
) : PageBase(systemProfile, userProfile, userSettings, commandManager) {
    override fun description(input: String) = "どの動物を買いますか？"

    override fun getChoiceBox(input: String): List<Choice> {
        val catalogs = fetchAnimalCatalogUseCase(InputImpl()).models
        return catalogs.mapIndexed(this::createChoice).toList()
    }

    private fun createChoice(idx: Int, catalog: AnimalCatalog) = factory(
        index = idx,
        value = catalog.code.raw(),
        label = "${catalog.name},値段:${catalog.price}z,体重:${catalog.weightMin}～${catalog.weightMax}",
        commandId = CommandId.L_SHOP_ANIMAL_BUY,
    )

    override fun getId() = CommandId.P_SHOP_ANIMAL_BUY
}