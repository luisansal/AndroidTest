package com.luisansal.jetpack.di

import com.luisansal.jetpack.model.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (val mApplication : MyApplication) {

    @Provides
    @Singleton
    fun providesApplication () : MyApplication {
        return mApplication
    }

}