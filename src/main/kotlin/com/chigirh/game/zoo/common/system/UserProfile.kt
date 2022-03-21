package com.chigirh.game.zoo.common.system

import com.chigirh.game.zoo.domain.model.money.Ledger
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentLinkedDeque

@Component
class UserProfile {
    var userName = "default"
    val money = Money(3000)
}

class Money(
    private var money: Int,
) {
    private val queue = ConcurrentLinkedDeque<Ledger>()

    fun doesLedgerNotEmpty() = !queue.isEmpty()

    fun poolLedger(): Ledger? = queue.poll()

    operator fun invoke() = money

    operator fun plus(i: Int) {
        calc(i)

    }

    operator fun minus(i: Int) {
        calc(0 - i)
    }

    @Synchronized
    private fun calc(i: Int) {
        money += i
        queue.add(Ledger(LocalDateTime.now(), i))
    }
}