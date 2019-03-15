package com.luisansal.jetpack.model

import android.app.Application
import com.luisansal.jetpack.di.AppComponent
import com.luisansal.jetpack.di.AppModule
import com.luisansal.jetpack.di.DaggerAppComponent
import com.luisansal.jetpack.di.RoomModule

class MyApplication : Application() {

    private lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .roomModule(RoomModule())
                .build()
    }

    fun getAppComponent(): AppComponent {
        return mAppComponent
    }
}