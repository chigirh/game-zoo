package com.chigirh.game.zoo.domain.model.cage

data class CageCatalog(
    val code: CageCode,
    val name: String,
    val size: Int,
    val weight: Int,
    val price:Int,
)
