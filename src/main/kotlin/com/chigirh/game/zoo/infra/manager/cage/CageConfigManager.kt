package com.chigirh.game.zoo.infra.manager.cage

import com.chigirh.game.zoo.domain.model.cage.CageCode
import org.springframework.stereotype.Component

/**
 * This class manages for cage config.
 */
@Component
class CageConfigManager(
    private val factories: List<CageCatalogFactory>
) {
    fun createCatalogs(coefficient: Int) = factories.map { it.createCatalog(coefficient) }.sortedBy { it.code }.toList()
    fun createCatalog(code: CageCode, coefficient: Int) = getFactory(code).createCatalog(coefficient)

    private fun getFactory(code: CageCode) = factories.find { it.getCode() == code }!!
}