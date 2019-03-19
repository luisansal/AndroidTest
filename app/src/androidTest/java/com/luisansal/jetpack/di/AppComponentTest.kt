package com.luisansal.jetpack.di

import android.app.Application
import com.luisansal.jetpack.LiveDataInteractorTest
import com.luisansal.jetpack.LiveDataPresenterTest
import com.luisansal.jetpack.model.MyApplication
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.support.AndroidSupportInjectionModule


@Singleton
@Component(modules = [ AndroidSupportInjectionModule::class, AppModule::class, BuildersModule::class])
interface AppComponentTest : AppComponent {

    @Component.Builder
    interface Builder : AppComponent.Builder {

        @BindsInstance
        override fun application(application: MyApplication): Builder

        override fun build(): AppComponentTest
    }

    fun inject(testToInject: LiveDataPresenterTest)
    fun inject(testToInject: LiveDataInteractorTest)
}