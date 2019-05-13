package com.luisansal.jetpack.di

import com.luisansal.jetpack.model.MyApplication
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.support.AndroidSupportInjectionModule


@Singleton
@Component(modules = [
    AppModule::class,
    AppBindingModule::class,
    ThreatExecutor::class,
    BuildersModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): AppComponent

    }

    fun inject(myApplication: MyApplication)
}