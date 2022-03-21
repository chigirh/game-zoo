package com.chigirh.game.zoo.infra.memory.repository

import com.chigirh.game.zoo.domain.model.animal.AnimalCode
import com.chigirh.game.zoo.domain.repository.AnimalCatalogRepository
import com.chigirh.game.zoo.domain.repository.AnimalRepository
import com.chigirh.game.zoo.infra.manager.animal.AnimalConfigManager
import org.springframework.stereotype.Repository

/**
 * Animal catalog Repository impl.
 */
@Repository
class AnimalCatalogRepositoryImpl(
    private val animalConfigManager: AnimalConfigManager,
    private val animalRepository: AnimalRepository,
) : AnimalCatalogRepository {
    override fun fetchAll() = animalConfigManager.createCatalogs(animalRepository.fetchMaxId())
    override fun fetchBy(code: AnimalCode) = animalConfigManager.createCatalog(code, animalRepository.fetchMaxId())
}