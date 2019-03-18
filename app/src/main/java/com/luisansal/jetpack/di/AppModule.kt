package com.luisansal.jetpack.di

import android.content.Context
import com.luisansal.jetpack.model.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.luisansal.jetpack.model.repository.UserRepository


@Module
class AppModule {

    @Provides
    fun provideContext(myApplication: MyApplication): Context {
        return myApplication.applicationContext
    }

    @Singleton
    @Provides
    fun provideUserRepository(context: Context): UserRepository {
        return UserRepository.newInstance(context)
    }

}