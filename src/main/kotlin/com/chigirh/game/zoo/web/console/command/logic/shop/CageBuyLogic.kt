package com.chigirh.game.zoo.web.console.command.logic.shop

import com.chigirh.game.zoo.common.system.SystemProfile
import com.chigirh.game.zoo.common.system.UserProfile
import com.chigirh.game.zoo.domain.model.cage.CageCode
import com.chigirh.game.zoo.domain.model.exception.BusinessException
import com.chigirh.game.zoo.domain.model.exception.SystemError
import com.chigirh.game.zoo.domain.usecase.cage.BuyCageUseCase
import com.chigirh.game.zoo.domain.usecase.cage.BuyCageUseCase.InputImpl
import com.chigirh.game.zoo.web.console.command.CommandId
import com.chigirh.game.zoo.web.console.command.logic.LogicBase
import com.chigirh.game.zoo.web.console.io.Display
import org.springframework.stereotype.Component

@Component("l_shop_0001")
class CageBuyLogic(
    private val userProfile: UserProfile,
    private val systemProfile: SystemProfile,
    private val buyCageUseCase: BuyCageUseCase,
) : LogicBase() {
    override fun logic(input: String) {
        runCatching {
            val code = CageCode.of(input)
            buyCageUseCase(InputImpl(code))
        }.fold(
            onSuccess = this::success,
            onFailure = this::failure,
        )
    }

    private fun success(output: BuyCageUseCase.OutputImpl) {
        val c = output.catalog
        Display("${c.price}zで$${c.name}を購入しました。")
    }

    private fun failure(e: Throwable) = when (e) {
        is IllegalArgumentException -> Display("正しい檻コードを入力してください。")
        is BusinessException -> Display(e.message)
        else -> throw SystemError("${getId().raw()}:${e.message}", e)
    }


    override fun getId() = CommandId.L_SHOP_CAGE_BUY
}