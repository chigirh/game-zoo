package com.chigirh.game.zoo.infra.manager.cage

import com.chigirh.game.zoo.domain.model.cage.*
import org.springframework.stereotype.Component

abstract class CageCatalogFactory(
    private val properties: CageProperties,
) {
    fun createCatalog(coefficient: Int) = CageCatalog(
        code = getCode(),
        name = properties().name,
        size = properties().capacity.size,
        weight = properties().capacity.size,
        price = properties().price * (coefficient + 1),
    )

    abstract fun getCode(): CageCode
}

@Component
class NormalCageCatalogFactory(
    normalCageProperties: NormalCageProperties,
) : CageCatalogFactory(normalCageProperties) {
    override fun getCode() = CageCode.NORMAL
}

@Component
class SmallCageCatalogFactory(
    smallCageProperties: SmallCageProperties,
) : CageCatalogFactory(smallCageProperties) {
    override fun getCode() = CageCode.SMALL
}

@Component
class LargeCageCatalogFactory(
    largeCageProperties: LargeCageProperties,
) : CageCatalogFactory(largeCageProperties) {
    override fun getCode() = CageCode.LARGE
}

@Component
class StrongCageCatalogFactory(
    strongCageProperties: StrongCageProperties,
) : CageCatalogFactory(strongCageProperties) {
    override fun getCode() = CageCode.STRONG
}
