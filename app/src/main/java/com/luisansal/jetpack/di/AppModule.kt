package com.luisansal.jetpack.di

import android.content.Context
import com.luisansal.jetpack.model.MyApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(myApplication: MyApplication): Context {
        return myApplication.applicationContext
    }

}