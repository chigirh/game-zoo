package com.chigirh.game.zoo.web.console.command.logic.shop

import com.chigirh.game.zoo.common.system.SystemProfile
import com.chigirh.game.zoo.common.system.UserProfile
import com.chigirh.game.zoo.domain.model.animal.AnimalCode
import com.chigirh.game.zoo.domain.model.exception.BusinessException
import com.chigirh.game.zoo.domain.model.exception.SystemError
import com.chigirh.game.zoo.domain.usecase.animal.BuyAnimalUseCase
import com.chigirh.game.zoo.domain.usecase.animal.BuyAnimalUseCase.InputImpl
import com.chigirh.game.zoo.domain.usecase.animal.BuyAnimalUseCase.OutputImpl
import com.chigirh.game.zoo.web.console.command.CommandId
import com.chigirh.game.zoo.web.console.command.logic.LogicBase
import com.chigirh.game.zoo.web.console.io.Display
import org.springframework.stereotype.Component

@Component("l_shop_0002")
class AnimalBuyLogic(
    private val userProfile: UserProfile,
    private val systemProfile: SystemProfile,
    private val buyAnimalUseCase: BuyAnimalUseCase,
) : LogicBase() {
    override fun logic(input: String) {
        runCatching {
            val code = AnimalCode.of(input)
            buyAnimalUseCase(InputImpl(code))
        }.fold(
            onSuccess = this::success,
            onFailure = this::failure,
        )
    }

    private fun success(output: OutputImpl) {
        val a = output.animal
        Display("${a.name}を購入しました。id:${a.id},名前:${a.name},体重:${a.weight}.時給:${a.hourlyWage}")
    }

    private fun failure(e: Throwable) = when (e) {
        is IllegalArgumentException -> Display("正しい動物コードを入力してください。")
        is BusinessException -> Display(e.message)
        else -> throw SystemError("${getId().raw()}:${e.message}", e)
    }


    override fun getId() = CommandId.L_SHOP_ANIMAL_BUY
}