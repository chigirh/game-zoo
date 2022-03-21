package com.chigirh.game.zoo.infra.memory.store

import com.chigirh.game.zoo.domain.model.animal.Animal
import org.springframework.stereotype.Repository

@Repository
class CageAnimalContactStore : Store<CageKey, MutableMap<AnimalKey, Animal>>()