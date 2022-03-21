package com.chigirh.game.zoo.domain.usecase.cage

import com.chigirh.game.zoo.common.system.UserProfile
import com.chigirh.game.zoo.domain.model.cage.CageBuyer.buy
import com.chigirh.game.zoo.domain.model.cage.CageCatalog
import com.chigirh.game.zoo.domain.model.cage.CageCode
import com.chigirh.game.zoo.domain.model.exception.BusinessException
import com.chigirh.game.zoo.domain.model.exception.ErrorCode
import com.chigirh.game.zoo.domain.repository.CageCatalogRepository
import com.chigirh.game.zoo.domain.repository.CageRepository
import com.chigirh.game.zoo.domain.usecase.Input
import com.chigirh.game.zoo.domain.usecase.Output
import com.chigirh.game.zoo.domain.usecase.UseCase
import com.chigirh.game.zoo.domain.usecase.UseCaseBase
import com.chigirh.game.zoo.domain.usecase.cage.BuyCageUseCase.InputImpl
import com.chigirh.game.zoo.domain.usecase.cage.BuyCageUseCase.OutputImpl

@UseCase
class BuyCageUseCase(
    private val userProfile: UserProfile,
    private val cageCatalogRepository: CageCatalogRepository,
    private val cageRepository: CageRepository,
) : UseCaseBase<InputImpl, OutputImpl>() {
    override fun useCase(input: InputImpl): OutputImpl {
        val catalog = cageCatalogRepository.fetchBy(input.cageCode)

        if (userProfile.money() < catalog.price) {
            throw BusinessException("所持金不足で檻を購入できませんでした。", null, ErrorCode.PERMISSION_DENIED)
        }

        val id = cageRepository.fetchMaxId()
        val cage = catalog.buy(id + 1)

        cageRepository.insert(cage)

        userProfile.money.minus(catalog.price)

        return OutputImpl(catalog)
    }

    data class InputImpl(
        val cageCode: CageCode,
    ) : Input {
        override fun useCaseName() = "檻を購入"
    }

    data class OutputImpl(
        val catalog: CageCatalog,
    ) : Output()
}

