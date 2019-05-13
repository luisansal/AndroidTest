package com.luisansal.jetpack.di

import com.luisansal.jetpack.common.executor.JobExecutor
import com.luisansal.jetpack.common.executor.PostExecutionThread
import com.luisansal.jetpack.common.executor.ThreadExecutor
import com.luisansal.jetpack.ui.UIThread
import dagger.Binds
import dagger.Module

@Module
abstract class ThreatExecutor {
    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

    @Binds
    abstract fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread
}