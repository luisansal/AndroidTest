package com.luisansal.jetpack.common.executor

import io.reactivex.Scheduler
import java.util.concurrent.Executor

interface ThreadExecutor : Executor {
    val scheduler: Scheduler
}