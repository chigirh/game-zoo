package com.chigirh.game.zoo.domain.repository

import com.chigirh.game.zoo.domain.model.animal.Animal
import com.chigirh.game.zoo.domain.model.animal.AnimalCode

/**
 * Animal Repository interface.
 */
interface AnimalRepository {
    fun fetchAll(): List<Animal>
    fun fetchMaxId(): Int
    fun fetchByKey(id: Int): Animal?
    fun fetchBy(code: AnimalCode): List<Animal>
    fun insert(animal: Animal)
}