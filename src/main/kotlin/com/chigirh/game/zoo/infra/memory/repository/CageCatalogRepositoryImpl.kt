package com.chigirh.game.zoo.infra.memory.repository

import com.chigirh.game.zoo.domain.model.cage.CageCode
import com.chigirh.game.zoo.domain.repository.CageCatalogRepository
import com.chigirh.game.zoo.domain.repository.CageRepository
import com.chigirh.game.zoo.infra.manager.cage.CageConfigManager
import org.springframework.stereotype.Repository

/**
 * Cage catalog Repository impl.
 */
@Repository
class CageCatalogRepositoryImpl(
    private val cageConfigManager: CageConfigManager,
    private val cageRepository: CageRepository,
) : CageCatalogRepository {
    override fun fetchAll() = cageConfigManager.createCatalogs(cageRepository.fetchMaxId())
    override fun fetchBy(code: CageCode) = cageConfigManager.createCatalog(code, cageRepository.fetchMaxId())
}