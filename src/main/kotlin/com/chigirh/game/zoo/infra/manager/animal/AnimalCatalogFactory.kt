package com.chigirh.game.zoo.infra.manager.animal

import com.chigirh.game.zoo.domain.model.animal.*
import org.springframework.stereotype.Component

abstract class AnimalCatalogFactory(
    private val properties: AnimalProperties,
) {
    fun createCatalog(coefficient: Int) = AnimalCatalog(
        code = getCode(),
        name = properties().name,
        weightMin = properties().weight.min,
        weightMax = properties().weight.max,
        hourlyWageBase = properties().hourlyWageBase,
        price = properties().price * (coefficient + 1),
    )

    abstract fun getCode(): AnimalCode
}

@Component
class DogCatalogFactory(
    dogProperties: DogProperties,
) : AnimalCatalogFactory(dogProperties) {
    override fun getCode() = AnimalCode.DOG
}

@Component
class CatCatalogFactory(
    catProperties: CatProperties,
) : AnimalCatalogFactory(catProperties) {
    override fun getCode() = AnimalCode.CAT
}

@Component
class HorseCatalogFactory(
    horseProperties: HorseProperties,
) : AnimalCatalogFactory(horseProperties) {
    override fun getCode() = AnimalCode.HORSE
}

@Component
class GiraffeCatalogFactory(
    giraffeProperties: GiraffeProperties,
) : AnimalCatalogFactory(giraffeProperties) {
    override fun getCode() = AnimalCode.GIRAFFE
}

@Component
class LionCatalogFactory(
    lionProperties: LionProperties,
) : AnimalCatalogFactory(lionProperties) {
    override fun getCode() = AnimalCode.LION
}