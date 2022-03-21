package com.chigirh.game.zoo.domain.usecase.cage

import com.chigirh.game.zoo.domain.model.exception.BusinessException
import com.chigirh.game.zoo.domain.model.exception.ErrorCode
import com.chigirh.game.zoo.domain.repository.AnimalRepository
import com.chigirh.game.zoo.domain.repository.CageRepository
import com.chigirh.game.zoo.domain.service.CageService
import com.chigirh.game.zoo.domain.usecase.Input
import com.chigirh.game.zoo.domain.usecase.Output
import com.chigirh.game.zoo.domain.usecase.UseCase
import com.chigirh.game.zoo.domain.usecase.UseCaseBase
import com.chigirh.game.zoo.domain.usecase.cage.PutAnimalUseCase.InputImpl

@UseCase
class PutAnimalUseCase(
    private val cageService: CageService,
    private val cageRepository: CageRepository,
    private val animalRepository: AnimalRepository,
) : UseCaseBase<InputImpl, Output>() {
    override fun useCase(input: InputImpl): Output {
        // fetch cage
        val cage = cageRepository.fetchByKey(input.cageId)
            ?: throw BusinessException("存在しない檻ID。", null, ErrorCode.NOT_FOUND)

        // fetch animal
        val animal = animalRepository.fetchByKey(input.animalId)
            ?: throw BusinessException("存在しない動物ID。", null, ErrorCode.NOT_FOUND)

        cageService.putAnimal(cage, animal)

        return Output()
    }

    data class InputImpl(
        val cageId: Int,
        val animalId: Int,
    ) : Input {
        override fun useCaseName() = "動物を檻に入れる。"
    }
}

