package com.luisansal.jetpack.di

import com.luisansal.jetpack.feature.LiveDataInteractorTest
import com.luisansal.jetpack.feature.LiveDataPresenterTest
import com.luisansal.jetpack.model.MyApplication
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class,
    BuildersModule::class,
    FragmentBindModule::class
])
interface AppComponentTest : AppComponent {

    @Component.Builder
    interface Builder : AppComponent.Builder {

        @BindsInstance
        override fun application(application: MyApplication): Builder

        override fun build(): AppComponentTest
    }

    fun inject(testToInject: LiveDataInteractorTest)
    fun inject(testToInject: LiveDataPresenterTest)
}