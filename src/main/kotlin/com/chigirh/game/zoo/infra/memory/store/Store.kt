package com.chigirh.game.zoo.infra.memory.store

import java.util.concurrent.ConcurrentHashMap
import java.util.function.Predicate

abstract class Store<K, V>(
    private val map: ConcurrentHashMap<K, V> = ConcurrentHashMap(),
) {
    fun raw() = map

    operator fun set(key: K, value: V) {
        map[key] = value
    }

    fun doesKeyNotExists(key: K) = !map.containsKey(key)

    operator fun get(key: K) = map[key]

    fun findAll() = map.values.toList()

    fun findByCondition(condition: Predicate<V>) = map.values.filter { condition.test(it) }.toList()
}