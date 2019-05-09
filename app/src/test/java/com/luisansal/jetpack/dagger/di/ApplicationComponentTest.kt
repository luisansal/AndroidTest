package com.luisansal.jetpack.dagger.di

import com.luisansal.jetpack.di.AppComponent
import com.luisansal.jetpack.di.AppModule
import com.luisansal.jetpack.di.BuildersModule
import com.luisansal.jetpack.di.FragmentBindModule
import com.luisansal.jetpack.feature.GuardarAuthorPersistenceTest
import com.luisansal.jetpack.feature.GuardarAuthorServiceTest
import com.luisansal.jetpack.model.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
//@Component(modules = [
//    AppModule::class,
//    BuildersModule::class,
//    FragmentBindModule::class,
//    AndroidSupportInjectionModule::class
//])
interface ApplicationComponentTest : AppComponent {

    @Component.Builder
    interface Builder : AppComponent .Builder {

        @BindsInstance
        override fun application(application: MyApplication): Builder

        override fun build(): ApplicationComponentTest

    }

    fun inject(testToInject: GuardarAuthorPersistenceTest)
    fun inject(testToInject: GuardarAuthorServiceTest)
}