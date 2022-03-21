package com.chigirh.game.zoo.domain.model.cage

import com.chigirh.game.zoo.domain.model.animal.Animal

data class Cage(
    val id: Int,
    val code: CageCode,
    val name: String,
    val limitSize: Int,
    val limitWeight: Int,
    val animals: List<Animal> = listOf()
) {
    fun getTotalWeight() = animals.sumOf { it.weight }
    fun getStorableWeight() = limitWeight - getTotalWeight()
    fun getStorableSize() = limitSize - animals.size
}
