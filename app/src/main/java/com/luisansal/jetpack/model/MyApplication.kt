package com.luisansal.jetpack.model

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.luisansal.jetpack.di.AppComponent
import com.luisansal.jetpack.di.DaggerAppComponent
import com.luisansal.jetpack.model.usecase.interfaces.AuthorUseCase
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


open class MyApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingAndroidFragmentInjector: DispatchingAndroidInjector<Fragment>

    lateinit var mAppComponent: AppComponent

    @Inject
    lateinit var authorUseCase: AuthorUseCase

    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.builder().application(this)
                .build()
        mAppComponent.inject(this)
        authorUseCase.guardarAuthorsEnLocal()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidFragmentInjector
    }
}