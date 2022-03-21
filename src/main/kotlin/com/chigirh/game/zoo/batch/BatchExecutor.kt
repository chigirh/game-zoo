package com.chigirh.game.zoo.batch

import com.chigirh.game.zoo.batch.task.BatchTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.stereotype.Component

@Component("batchExecutor")
class BatchExecutor(
    private val batchTasks: List<BatchTask>
) {
    operator fun invoke() {
        batchTasks.forEach { CoroutineScope(Dispatchers.Default).launch { it() } }
    }
}