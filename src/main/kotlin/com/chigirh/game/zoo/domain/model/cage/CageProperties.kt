package com.chigirh.game.zoo.domain.model.cage

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

interface CageProperties {
    operator fun invoke(): CageConfig
}

data class CageConfig(
    val name: String,
    val capacity: CageCapacity,
    val price: Int,
)

data class CageCapacity(
    val size: Int,
    val weight: Int,
)

@ConstructorBinding
@ConfigurationProperties("cage")
data class NormalCageProperties(
    val normal: CageConfig,
) : CageProperties {
    override fun invoke() = normal
}

@ConstructorBinding
@ConfigurationProperties("cage")
data class SmallCageProperties(
    val small: CageConfig,
) : CageProperties {
    override fun invoke() = small
}


@ConstructorBinding
@ConfigurationProperties("cage")
data class LargeCageProperties(
    val large: CageConfig,
) : CageProperties {
    override fun invoke() = large
}


@ConstructorBinding
@ConfigurationProperties("cage")
data class StrongCageProperties(
    val strong: CageConfig,
) : CageProperties {
    override fun invoke() = strong
}
