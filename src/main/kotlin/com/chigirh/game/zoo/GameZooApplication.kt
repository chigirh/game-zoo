package com.chigirh.game.zoo

import com.chigirh.game.zoo.batch.BatchExecutor
import com.chigirh.game.zoo.web.console.command.CommandId
import com.chigirh.game.zoo.common.context.ApplicationContextHolder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import kotlin.system.exitProcess

@SpringBootApplication
@ConfigurationPropertiesScan
class GameZooApplication

fun main(args: Array<String>) {
	ApplicationContextHolder.application = runApplication<GameZooApplication>(*args)

	// batch
	val batchExecutor = ApplicationContextHolder
		.getBean("batchExecutor")
		.let { if (it is BatchExecutor) it else throw IllegalArgumentException("batchExecutor not found.") }
	batchExecutor()

	// ui
	val rootPage = ApplicationContextHolder.getCommand(CommandId.P_ROOT)
	rootPage("")

	exitProcess(1)
}
