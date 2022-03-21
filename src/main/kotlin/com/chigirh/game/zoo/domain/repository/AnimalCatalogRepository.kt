package com.chigirh.game.zoo.domain.repository

import com.chigirh.game.zoo.domain.model.animal.AnimalCatalog
import com.chigirh.game.zoo.domain.model.animal.AnimalCode

/**
 * Animal catalog Repository interface.
 */
interface AnimalCatalogRepository {
    fun fetchAll(): List<AnimalCatalog>
    fun fetchBy(code: AnimalCode): AnimalCatalog
}