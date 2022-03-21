package com.chigirh.game.zoo.infra.memory.store

import com.chigirh.game.zoo.domain.model.animal.Animal
import org.springframework.stereotype.Repository

@Repository
class AnimalStore : Store<AnimalKey, Animal>()

fun Animal.key() = AnimalKey(this.id)

data class AnimalKey(
    val value: Int
)