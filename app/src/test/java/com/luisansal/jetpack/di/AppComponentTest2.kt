package com.luisansal.jetpack.di

import com.luisansal.jetpack.LiveDataInteractorTest2
import com.luisansal.jetpack.LiveDataPresenterTest2
import com.luisansal.jetpack.model.MyApplication
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.support.AndroidSupportInjectionModule


@Singleton
@Component(modules = [ AndroidSupportInjectionModule::class, AppModule::class, BuildersModule::class])
interface AppComponentTest2 : AppComponent {

    @Component.Builder
    interface Builder : AppComponent.Builder {

        @BindsInstance
        override fun application(application: MyApplication): Builder

        override fun build(): AppComponentTest2
    }

    fun inject(testToInject: LiveDataInteractorTest2)
    fun inject(testToInject: LiveDataPresenterTest2)
}