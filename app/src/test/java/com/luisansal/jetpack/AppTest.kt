package com.luisansal.jetpack

import com.luisansal.jetpack.dagger.di.DaggerAppComponentTest
import com.luisansal.jetpack.model.MyApplication

class AppTest : MyApplication() {

//    override fun isUnitTesting(): Boolean {
//        return true
//    }

    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponentTest.builder().application(this)
                .build()
        mAppComponent.inject(this)
    }

}