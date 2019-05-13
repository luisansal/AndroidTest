package com.luisansal.jetpack.di

import com.luisansal.jetpack.model.usecase.AuthorUseCaseImpl
import com.luisansal.jetpack.model.usecase.UserUseCaseImpl
import com.luisansal.jetpack.model.usecase.interfaces.AuthorUseCase
import com.luisansal.jetpack.model.usecase.interfaces.UserUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppBindingModule {

    @Binds
    abstract fun provideUserUseCase(userUseCase: UserUseCaseImpl): UserUseCase

    @Binds
    abstract fun provideAuthorUseCase(authorUseCaseImpl: AuthorUseCaseImpl): AuthorUseCase
}