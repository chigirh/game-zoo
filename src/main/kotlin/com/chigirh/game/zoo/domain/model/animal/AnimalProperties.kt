package com.chigirh.game.zoo.domain.model.animal

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

interface AnimalProperties {
    operator fun invoke(): AnimalConfig
}

data class AnimalConfig(
    val name: String,
    val weight: AnimalWeight,
    val hourlyWageBase: Int,
    val price: Int,
)

data class AnimalWeight(
    val min: Int,
    val max: Int,
)

@ConstructorBinding
@ConfigurationProperties("animal")
data class DogProperties(
    val dog: AnimalConfig,
) : AnimalProperties {
    override fun invoke() = dog
}

@ConstructorBinding
@ConfigurationProperties("animal")
data class CatProperties(
    val cat: AnimalConfig,
) : AnimalProperties {
    override fun invoke() = cat
}


@ConstructorBinding
@ConfigurationProperties("animal")
data class HorseProperties(
    val horse: AnimalConfig,
) : AnimalProperties {
    override fun invoke() = horse
}


@ConstructorBinding
@ConfigurationProperties("animal")
data class GiraffeProperties(
    val giraffe: AnimalConfig,
) : AnimalProperties {
    override fun invoke() = giraffe
}

@ConstructorBinding
@ConfigurationProperties("animal")
data class LionProperties(
    val lion: AnimalConfig,
) : AnimalProperties {
    override fun invoke() = lion
}
