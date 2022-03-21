package com.chigirh.game.zoo.domain.model.exception

open class BusinessException(
    override val message: String,
    override val cause: Throwable?,
    val code: ErrorCode,
) : RuntimeException(message, cause)

enum class ErrorCode {
    BAD_REQUEST,
    ALREADY_EXISTS,
    NOT_FOUND,
    PERMISSION_DENIED,

}