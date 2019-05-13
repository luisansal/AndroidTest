package com.luisansal.jetpack.common.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}