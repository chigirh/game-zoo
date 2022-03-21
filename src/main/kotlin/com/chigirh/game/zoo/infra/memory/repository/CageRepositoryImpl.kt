package com.chigirh.game.zoo.infra.memory.repository

import com.chigirh.game.zoo.domain.model.cage.Cage
import com.chigirh.game.zoo.domain.model.cage.CageCode
import com.chigirh.game.zoo.domain.repository.CageRepository
import com.chigirh.game.zoo.infra.memory.store.CageKey
import com.chigirh.game.zoo.infra.memory.store.CageStore
import com.chigirh.game.zoo.infra.memory.store.key
import org.springframework.stereotype.Repository
import org.springframework.util.CollectionUtils

/**
 * Cage Repository impl.
 */
@Repository
class CageRepositoryImpl(
    private val store: CageStore,
) : CageRepository {
    override fun fetchAll(): List<Cage> = store.findAll()

    override fun fetchMaxId() = if (!CollectionUtils.isEmpty(store.findAll())) {
        store.findAll().maxOf { it.id }
    } else 0

    override fun fetchByKey(id: Int) = store[CageKey(id)]

    override fun fetchBy(code: CageCode) = store.findByCondition { code == it.code }

    override fun insert(cage: Cage) {
        store[cage.key()] = cage
    }
}