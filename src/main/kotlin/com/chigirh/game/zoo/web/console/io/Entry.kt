package com.chigirh.game.zoo.web.console.io

object Entry {
    operator fun invoke(): String {
        print("> please input:")
        return readLine()!!
    }
}