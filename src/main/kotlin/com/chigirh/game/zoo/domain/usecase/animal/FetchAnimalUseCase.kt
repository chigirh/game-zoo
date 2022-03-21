package com.chigirh.game.zoo.domain.usecase.animal

import com.chigirh.game.zoo.domain.model.animal.Animal
import com.chigirh.game.zoo.domain.model.animal.AnimalCode
import com.chigirh.game.zoo.domain.model.exception.BusinessException
import com.chigirh.game.zoo.domain.model.exception.ErrorCode
import com.chigirh.game.zoo.domain.repository.AnimalInCageRepository
import com.chigirh.game.zoo.domain.repository.AnimalRepository
import com.chigirh.game.zoo.domain.usecase.Input
import com.chigirh.game.zoo.domain.usecase.Output
import com.chigirh.game.zoo.domain.usecase.UseCase
import com.chigirh.game.zoo.domain.usecase.UseCaseBase
import com.chigirh.game.zoo.domain.usecase.animal.FetchAnimalUseCase.InputImpl
import com.chigirh.game.zoo.domain.usecase.animal.FetchAnimalUseCase.OutputImpl

@UseCase
class FetchAnimalUseCase(
    private val animalRepository: AnimalRepository,
    private val animalInCageRepository: AnimalInCageRepository,
) : UseCaseBase<InputImpl, OutputImpl>() {
    override fun useCase(input: InputImpl): OutputImpl {
        return when (input.mode) {
            Mode.ALL -> allMode()
            Mode.ANIMAL_ID -> animalIdMode(input)
            Mode.CAGE_ID -> cageIdMode(input)
            Mode.ANIMAL_CODE -> animalCodeMode(input)
            Mode.NOT_IN_CAGE -> notInCageMode()
        }
    }

    private fun allMode() = OutputImpl(animalRepository.fetchAll())
    private fun animalIdMode(input: InputImpl): OutputImpl {
        val animalId = input.animalId ?: throw BusinessException(
            message = "validation error. animal id is null",
            cause = null,
            code = ErrorCode.BAD_REQUEST,
        )
        val animal =
            animalRepository.fetchByKey(animalId) ?: throw BusinessException("存在しない動物ID。", null, ErrorCode.NOT_FOUND)
        return OutputImpl(listOf(animal))
    }

    private fun cageIdMode(input: InputImpl): OutputImpl {
        val cageId = input.cageId ?: throw BusinessException(
            message = "validation error. cage id is null",
            cause = null,
            code = ErrorCode.BAD_REQUEST,
        )
        val animals = animalInCageRepository.fetchAnimalsBy(cageId)
        return OutputImpl(animals)
    }

    private fun animalCodeMode(input: InputImpl): OutputImpl {
        val code = input.animalCode ?: throw BusinessException(
            message = "validation error. animal code is null",
            cause = null,
            code = ErrorCode.BAD_REQUEST,
        )
        val animals = animalRepository.fetchBy(code)
        return OutputImpl(animals)
    }

    private fun notInCageMode() = OutputImpl(animalInCageRepository.fetchByNotInCage())

    data class InputImpl(
        val mode: Mode,
        val animalId: Int? = null,
        val cageId: Int? = null,
        val animalCode: AnimalCode? = null,
    ) : Input {
        override fun useCaseName() = "動物一覧取得"
    }

    enum class Mode {
        ALL,
        ANIMAL_ID,
        CAGE_ID,
        ANIMAL_CODE,
        NOT_IN_CAGE,
    }

    data class OutputImpl(
        val models: List<Animal>
    ) : Output()
}

