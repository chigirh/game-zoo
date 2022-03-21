package com.chigirh.game.zoo.infra.memory.repository

import com.chigirh.game.zoo.domain.model.animal.Animal
import com.chigirh.game.zoo.domain.model.animal.AnimalCode
import com.chigirh.game.zoo.domain.repository.AnimalRepository
import com.chigirh.game.zoo.infra.memory.store.AnimalKey
import com.chigirh.game.zoo.infra.memory.store.AnimalStore
import com.chigirh.game.zoo.infra.memory.store.key
import org.springframework.stereotype.Repository
import org.springframework.util.CollectionUtils

/**
 * Animal Repository impl.
 */
@Repository
class AnimalRepositoryImpl(
    private val store: AnimalStore
) : AnimalRepository {
    override fun fetchAll() = store.findAll()

    override fun fetchMaxId() = if (!CollectionUtils.isEmpty(store.findAll())) {
        store.findAll().maxOf { it.id }
    } else 0

    override fun fetchByKey(id: Int) = store[AnimalKey(id)]

    override fun fetchBy(code: AnimalCode) = store.findByCondition { code == it.code }

    override fun insert(animal: Animal) {
        store[animal.key()] = animal
    }
}