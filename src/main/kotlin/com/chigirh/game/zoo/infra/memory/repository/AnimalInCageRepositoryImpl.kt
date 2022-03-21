package com.chigirh.game.zoo.infra.memory.repository

import com.chigirh.game.zoo.domain.model.animal.Animal
import com.chigirh.game.zoo.domain.model.cage.Cage
import com.chigirh.game.zoo.domain.repository.AnimalInCageRepository
import com.chigirh.game.zoo.domain.repository.CageRepository
import com.chigirh.game.zoo.infra.memory.store.CageAnimalContactStore
import com.chigirh.game.zoo.infra.memory.store.key
import org.springframework.stereotype.Repository

/**
 * Animal in the cage repository impl.
 */
@Repository
class AnimalInCageRepositoryImpl(
    private val store: CageAnimalContactStore,
    private val cageRepository: CageRepository,
) : AnimalInCageRepository {
    override fun insert(cage: Cage, animal: Animal) {
        val key = cage.key()
        if (store.doesKeyNotExists(key)) {
            store[key] = HashMap()
        }

        val map = store[key]!!
        map[animal.key()] = animal
    }

    override fun delete(cage: Cage, animal: Animal) {
        val key = cage.key()
        val map = store[key]!!
        map.remove(animal.key())
    }

    override fun fetchCageBy(animal: Animal) =
        store.raw().entries.find { it.value.containsKey(animal.key()) }?.let { cageRepository.fetchByKey(it.key.value) }
}