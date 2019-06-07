package com.luisansal.jetpack.dagger.di

import com.luisansal.jetpack.di.*
import com.luisansal.jetpack.feature.nuevoauthor.persistencia.GuardarAuthorPersistenceTest
import com.luisansal.jetpack.feature.listadopaginadouser.LiveDataInteractorTest
import com.luisansal.jetpack.feature.listadopaginadouser.LiveDataPresenterTest
import com.luisansal.jetpack.feature.login.presenter.LoginPresenterTest
import com.luisansal.jetpack.feature.nuevoauthor.api.AuthorApiTest
import com.luisansal.jetpack.feature.nuevoauthor.api.LoginApiTest
import com.luisansal.jetpack.feature.nuevoauthor.presenter.NewAuthorPresenterTest
import com.luisansal.jetpack.feature.nuevoauthor.usecase.NewAuthorUseCaseTest
import com.luisansal.jetpack.model.MyApplication
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.support.AndroidSupportInjectionModule


@Singleton
@Component(modules = [
    AppModule::class,
    AppBindingModule::class,
    BuildersModule::class,
    ThreadExecutorModuleTest::class,
    ApiRestModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponentTest : AppComponent {

    @Component.Builder
    interface Builder : AppComponent.Builder {

        @BindsInstance
        override fun application(application: MyApplication): Builder

        override fun build(): AppComponentTest

    }

    fun inject(testToInject: LiveDataPresenterTest)
    fun inject(testToInject: LiveDataInteractorTest)
    fun inject(testToInject: GuardarAuthorPersistenceTest)
    fun inject(testToInject: NewAuthorPresenterTest)
    fun inject(testToInject: NewAuthorUseCaseTest)
    fun inject(testToInject: AuthorApiTest)
    fun inject(testToInject: LoginApiTest)
    fun inject(testToInject: LoginPresenterTest)
}