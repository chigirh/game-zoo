package com.chigirh.game.zoo.domain.repository

import com.chigirh.game.zoo.domain.model.animal.Animal
import com.chigirh.game.zoo.domain.model.cage.Cage

/**
 * Animal in the cage repository interface.
 */
interface AnimalInCageRepository {
    fun insert(cage: Cage, animal: Animal)
    fun delete(cage: Cage, animal: Animal)
    fun fetchCageBy(animal: Animal): Cage?
}