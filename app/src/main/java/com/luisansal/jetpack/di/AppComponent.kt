package com.luisansal.jetpack.di

import com.luisansal.jetpack.MainActivity
import com.luisansal.jetpack.model.MyApplication
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.support.AndroidSupportInjectionModule


@Singleton
@Component(modules = [ AndroidSupportInjectionModule::class, AppModule::class, BuildersModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MyApplication): Builder

//        fun appModule(appModule: AppModule) : Builder

        fun build(): AppComponent
    }

    fun inject(myApplication: MyApplication)

//    fun inject(myActivity: MainActivity)
}