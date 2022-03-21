package com.chigirh.game.zoo.domain.usecase.cage

import com.chigirh.game.zoo.domain.model.cage.Cage
import com.chigirh.game.zoo.domain.model.exception.BusinessException
import com.chigirh.game.zoo.domain.model.exception.ErrorCode
import com.chigirh.game.zoo.domain.repository.CageRepository
import com.chigirh.game.zoo.domain.usecase.Input
import com.chigirh.game.zoo.domain.usecase.Output
import com.chigirh.game.zoo.domain.usecase.UseCase
import com.chigirh.game.zoo.domain.usecase.UseCaseBase
import com.chigirh.game.zoo.domain.usecase.cage.FetchCageUseCase.InputImpl
import com.chigirh.game.zoo.domain.usecase.cage.FetchCageUseCase.OutputImpl

@UseCase
class FetchCageUseCase(
    private val cageRepository: CageRepository,
) : UseCaseBase<InputImpl, OutputImpl>() {
    override fun useCase(input: InputImpl): OutputImpl {
        val cageId = input.cageId ?: return OutputImpl(cageRepository.fetchAll())

        val cage = cageRepository.fetchByKey(cageId) ?: throw BusinessException("存在しない檻ID。", null, ErrorCode.NOT_FOUND)

        return OutputImpl(listOf(cage))
    }

    data class InputImpl(
        val cageId: Int?
    ) : Input {
        override fun useCaseName() = "檻一覧取得"
    }

    data class OutputImpl(
        val models: List<Cage>
    ) : Output()
}

