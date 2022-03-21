package com.chigirh.game.zoo.domain.model.animal

object AnimalBuyer {
    fun AnimalCatalog.buy(id: Int): Animal {
        val range = (this.weightMin..this.weightMax)
        val weight = range.random()
        return Animal(
            id = id,
            code = this.code,
            name = this.name,
            weight = weight,
            hourlyWage = this.hourlyWageBase * weight,
        )
    }
}