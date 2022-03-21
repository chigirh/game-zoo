package com.chigirh.game.zoo.domain.usecase.cage

import com.chigirh.game.zoo.domain.model.cage.CageCatalog
import com.chigirh.game.zoo.domain.repository.CageCatalogRepository
import com.chigirh.game.zoo.domain.usecase.Input
import com.chigirh.game.zoo.domain.usecase.Output
import com.chigirh.game.zoo.domain.usecase.UseCase
import com.chigirh.game.zoo.domain.usecase.UseCaseBase
import com.chigirh.game.zoo.domain.usecase.cage.FetchCageCatalogUseCase.InputImpl
import com.chigirh.game.zoo.domain.usecase.cage.FetchCageCatalogUseCase.OutputImpl

@UseCase
class FetchCageCatalogUseCase(
    private val cageCatalogRepository: CageCatalogRepository,
) : UseCaseBase<InputImpl, OutputImpl>() {
    override fun useCase(input: InputImpl) = cageCatalogRepository.fetchAll().run { OutputImpl(this) }

    class InputImpl : Input {
        override fun useCaseName() = "檻のカタログを取得"
    }

    data class OutputImpl(
        val models: List<CageCatalog>
    ) : Output()
}

