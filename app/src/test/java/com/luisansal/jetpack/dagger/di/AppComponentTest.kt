package com.luisansal.jetpack.dagger.di

import com.luisansal.jetpack.di.AppComponent
import com.luisansal.jetpack.di.AppModule
import com.luisansal.jetpack.di.BuildersModule
import com.luisansal.jetpack.di.FragmentBindModule
import com.luisansal.jetpack.feature.GuardarAuthorPersistenceTest
import com.luisansal.jetpack.feature.LiveDataInteractorTest
import com.luisansal.jetpack.feature.LiveDataPresenterTest
import com.luisansal.jetpack.feature.NewAuthorPresenterTest
import com.luisansal.jetpack.model.MyApplication
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.support.AndroidSupportInjectionModule


@Singleton
@Component(modules = [
    AppModule::class,
    BuildersModule::class,
    FragmentBindModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponentTest : AppComponent {

    @Component.Builder
    interface Builder : AppComponent.Builder {

        @BindsInstance
        override fun application(application: MyApplication): Builder

        override fun build(): AppComponentTest

    }

    fun inject(testToInject: GuardarAuthorPersistenceTest)
    fun inject(testToInject: NewAuthorPresenterTest)
    fun inject(testToInject: LiveDataPresenterTest)
    fun inject(testToInject: LiveDataInteractorTest)
}