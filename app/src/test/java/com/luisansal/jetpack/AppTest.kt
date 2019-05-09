package com.luisansal.jetpack

import android.app.Application
import com.luisansal.jetpack.model.MyApplication
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.schedulers.Schedulers

class AppTest : MyApplication() {

//    override fun isUnitTesting(): Boolean {
//        return true
//    }

//    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
//        mAppComponent = DaggerApplicationComponentTest.builder().application(this)
//                .build()
//        applicationComponent!!.inject(this)
//        return applicationComponent as ApplicationComponent
//    }
}