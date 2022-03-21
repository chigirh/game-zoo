package com.chigirh.game.zoo.domain.model.animal

class AnimalCatalog(
    val code: AnimalCode,
    val name: String,
    val weightMin: Int,
    val weightMax: Int,
    val hourlyWageBase: Int,
    val price: Int,
)