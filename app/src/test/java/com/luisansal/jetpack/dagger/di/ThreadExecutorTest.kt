package com.luisansal.jetpack.dagger.di

import com.luisansal.jetpack.common.executor.PostExecutionThread
import com.luisansal.jetpack.common.executor.ThreadExecutor
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import org.mockito.Mockito

@Module
class ThreadExecutorTest {
    @Provides
    fun provideThreadExecutor () : ThreadExecutor{
        val mock = Mockito.mock(ThreadExecutor::class.java)
        Mockito.`when`(mock.scheduler).thenReturn(Schedulers.trampoline())
        return mock
    }

    @Provides
    fun providePostExecutionThread () : PostExecutionThread {
        val mock = Mockito.mock(PostExecutionThread::class.java)
        Mockito.`when`(mock.scheduler).thenReturn(Schedulers.trampoline())
        return mock
    }
}