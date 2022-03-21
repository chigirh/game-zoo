package com.chigirh.game.zoo.domain.usecase.animal

import com.chigirh.game.zoo.domain.model.animal.AnimalCatalog
import com.chigirh.game.zoo.domain.repository.AnimalCatalogRepository
import com.chigirh.game.zoo.domain.usecase.Input
import com.chigirh.game.zoo.domain.usecase.Output
import com.chigirh.game.zoo.domain.usecase.UseCase
import com.chigirh.game.zoo.domain.usecase.UseCaseBase
import com.chigirh.game.zoo.domain.usecase.animal.FetchAnimalCatalogUseCase.InputImpl
import com.chigirh.game.zoo.domain.usecase.animal.FetchAnimalCatalogUseCase.OutputImpl

@UseCase
class FetchAnimalCatalogUseCase(
    private val animalCatalogRepository: AnimalCatalogRepository,
) : UseCaseBase<InputImpl, OutputImpl>() {
    override fun useCase(input: InputImpl) = animalCatalogRepository.fetchAll().run { OutputImpl(this) }

    class InputImpl : Input {
        override fun useCaseName() = "動物のカタログを取得"
    }

    data class OutputImpl(
        val models: List<AnimalCatalog>
    ) : Output()
}

