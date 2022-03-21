package com.chigirh.game.zoo.batch.task

import com.chigirh.game.zoo.common.system.SystemProfile
import com.chigirh.game.zoo.common.system.UserProfile
import com.chigirh.game.zoo.domain.repository.AnimalRepository
import org.springframework.stereotype.Component

@Component
class SalesTask(
    override val systemProfile: SystemProfile,
    private val userProfile: UserProfile,
    private val animalRepository: AnimalRepository,
) : BatchTask(systemProfile) {
    override fun task() {
        val sales = animalRepository.fetchAll().sumOf { it.hourlyWage }
        userProfile.money + sales
    }

    override fun interval() = 1000L
}