package com.chigirh.game.zoo.domain.repository

import com.chigirh.game.zoo.domain.model.cage.Cage
import com.chigirh.game.zoo.domain.model.cage.CageCode

/**
 * Cage Repository interface.
 */
interface CageRepository {
    fun fetchAll(): List<Cage>
    fun fetchMaxId(): Int
    fun fetchByKey(id: Int): Cage?
    fun fetchBy(code: CageCode): List<Cage>
    fun insert(cage: Cage)
}