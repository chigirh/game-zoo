package com.chigirh.game.zoo.infra.memory.store

import com.chigirh.game.zoo.domain.model.cage.Cage
import org.springframework.stereotype.Repository

@Repository
class CageStore : Store<CageKey, Cage>()

fun Cage.key() = CageKey(this.id)

data class CageKey(
    val value: Int
)