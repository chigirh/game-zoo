package com.chigirh.game.zoo.domain.model.cage

object CageBuyer {
    fun CageCatalog.buy(id: Int) = Cage(
        id = id,
        code = this.code,
        name = this.name,
        limitSize = this.size,
        limitWeight = this.size,
    )
}