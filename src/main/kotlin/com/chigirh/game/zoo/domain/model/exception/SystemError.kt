package com.chigirh.game.zoo.domain.model.exception

class SystemError(
    override val message: String,
    override val cause: Throwable?,
) : RuntimeException(message, cause)