package com.chigirh.game.zoo.domain.service

import com.chigirh.game.zoo.domain.model.animal.Animal
import com.chigirh.game.zoo.domain.model.cage.Cage
import com.chigirh.game.zoo.domain.model.exception.BusinessException
import com.chigirh.game.zoo.domain.model.exception.ErrorCode
import com.chigirh.game.zoo.domain.repository.AnimalInCageRepository
import org.springframework.stereotype.Component

@Component
class CageService(
    val animalInCageRepository: AnimalInCageRepository,
) {
    fun canAnimalCaged(cage: Cage, anima: Animal) {
        val existsCage = animalInCageRepository.fetchCageBy(anima)
        if (existsCage != null) {
            throw BusinessException("${existsCage.id}の檻にすでに入っています。", null, ErrorCode.ALREADY_EXISTS)
        }

        if (cage.maxSize <= cage.animals.size) {
            throw BusinessException(
                "${cage.id}の檻にこれ以上動物を入れることができません。max size:${cage.maxSize}",
                null,
                ErrorCode.PERMISSION_DENIED
            )
        }

        val totalWeight = cage.getTotalWeight()

        if (cage.limitWeight < totalWeight + anima.weight) {
            throw BusinessException(
                "${cage.id}の檻にこれ以上動物を入れることができません。limit weight:${cage.limitWeight}",
                null,
                ErrorCode.PERMISSION_DENIED
            )
        }

        animalInCageRepository.insert(cage, anima)
    }
}