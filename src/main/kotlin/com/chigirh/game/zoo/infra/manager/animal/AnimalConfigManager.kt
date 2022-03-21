package com.chigirh.game.zoo.infra.manager.animal

import com.chigirh.game.zoo.domain.model.animal.AnimalCode
import com.chigirh.game.zoo.domain.model.cage.CageCode
import org.springframework.stereotype.Component

/**
 * This class manages for animal config.
 */
@Component
class AnimalConfigManager(
    private val factories: List<AnimalCatalogFactory>
) {
    fun createCatalogs(coefficient: Int) = factories.map { it.createCatalog(coefficient) }.sortedBy { it.code }.toList()
    fun createCatalog(code: AnimalCode, coefficient: Int) = getFactory(code).createCatalog(coefficient)

    private fun getFactory(code: AnimalCode) = factories.find { it.getCode() == code }!!
}