package com.chigirh.game.zoo.web.console.command.page.shop

import com.chigirh.game.zoo.common.system.SystemProfile
import com.chigirh.game.zoo.common.system.UserProfile
import com.chigirh.game.zoo.common.system.UserSettings
import com.chigirh.game.zoo.domain.model.cage.CageCatalog
import com.chigirh.game.zoo.domain.usecase.cage.FetchCageCatalogUseCase
import com.chigirh.game.zoo.domain.usecase.cage.FetchCageCatalogUseCase.InputImpl
import com.chigirh.game.zoo.web.console.command.Choice
import com.chigirh.game.zoo.web.console.command.ChoiceFactory
import com.chigirh.game.zoo.web.console.command.CommandId
import com.chigirh.game.zoo.web.console.command.CommandManager
import com.chigirh.game.zoo.web.console.command.page.PageBase
import org.springframework.stereotype.Component

@Component("p_shop_0002")
class CageBuyPage(
    override val systemProfile: SystemProfile,
    override val userProfile: UserProfile,
    override val userSettings: UserSettings,
    override val commandManager: CommandManager,
    private val fetchCageCatalogUseCase: FetchCageCatalogUseCase,
    private val factory: ChoiceFactory,
) : PageBase(systemProfile, userProfile, userSettings, commandManager) {
    override fun description(input: String) = "どの檻を買いますか？"

    override fun getChoiceBox(input: String): List<Choice> {
        val catalogs = fetchCageCatalogUseCase(InputImpl()).models
        return catalogs.mapIndexed(this::createChoice).toList()
    }

    private fun createChoice(idx: Int, catalog: CageCatalog) = factory(
        index = idx,
        value = catalog.code.raw(),
        label = "商品名:${catalog.name},値段:${catalog.price}z,動物の最大数:${catalog.size},最大重量:${catalog.weight}",
        commandId = CommandId.L_SHOP_CAGE_BUY,
    )

    override fun getId() = CommandId.P_SHOP_CAGE_BUY
}