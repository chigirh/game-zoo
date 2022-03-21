package com.chigirh.game.zoo.domain.usecase.animal

import com.chigirh.game.zoo.common.system.UserProfile
import com.chigirh.game.zoo.domain.model.animal.Animal
import com.chigirh.game.zoo.domain.model.animal.AnimalBuyer.buy
import com.chigirh.game.zoo.domain.model.animal.AnimalCode
import com.chigirh.game.zoo.domain.model.exception.BusinessException
import com.chigirh.game.zoo.domain.model.exception.ErrorCode
import com.chigirh.game.zoo.domain.repository.AnimalCatalogRepository
import com.chigirh.game.zoo.domain.repository.AnimalRepository
import com.chigirh.game.zoo.domain.usecase.Input
import com.chigirh.game.zoo.domain.usecase.Output
import com.chigirh.game.zoo.domain.usecase.UseCase
import com.chigirh.game.zoo.domain.usecase.UseCaseBase
import com.chigirh.game.zoo.domain.usecase.animal.BuyAnimalUseCase.InputImpl
import com.chigirh.game.zoo.domain.usecase.animal.BuyAnimalUseCase.OutputImpl

@UseCase
class BuyAnimalUseCase(
    private val userProfile: UserProfile,
    private val animalCatalogRepository: AnimalCatalogRepository,
    private val animalRepository: AnimalRepository,
) : UseCaseBase<InputImpl, OutputImpl>() {
    override fun useCase(input: InputImpl): OutputImpl {
        val catalog = animalCatalogRepository.fetchBy(input.cageCode)

        if (userProfile.money() < catalog.price) {
            throw BusinessException("所持金不足で動物を購入できませんでした。", null, ErrorCode.PERMISSION_DENIED)
        }

        val id = animalRepository.fetchMaxId()
        val animal = catalog.buy(id + 1)

        animalRepository.insert(animal)

        userProfile.money.minus(catalog.price)

        return OutputImpl(animal)
    }

    data class InputImpl(
        val cageCode: AnimalCode,
    ) : Input {
        override fun useCaseName() = "動物を購入"
    }

    data class OutputImpl(
        val animal: Animal,
    ) : Output()
}

