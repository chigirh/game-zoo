package com.chigirh.game.zoo.domain.usecase

/**
 * UseCase base class.
 */
abstract class UseCaseBase<I : Input, O : Output> {
    operator fun invoke(input: I): O {
        try {
            inputModification(input)
            return useCase(input)
        } finally {
            //
        }
    }

    open fun inputModification(input: I) {}

    abstract fun useCase(input: I): O
}

interface Input {
    fun useCaseName(): String = ""
}

open class Output