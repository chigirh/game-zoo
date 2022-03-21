package com.chigirh.game.zoo.domain.repository

import com.chigirh.game.zoo.domain.model.cage.CageCatalog
import com.chigirh.game.zoo.domain.model.cage.CageCode

/**
 * Cage catalog Repository interface.
 */
interface CageCatalogRepository {
    fun fetchAll(): List<CageCatalog>
    fun fetchBy(code: CageCode): CageCatalog
}