package com.luisansal.jetpack.di

import com.luisansal.jetpack.model.repository.AuthorCloudRepositoryImpl
import com.luisansal.jetpack.model.repository.AuthorRepositoryImpl
import com.luisansal.jetpack.model.repository.LoginCloudRepositoryImpl
import com.luisansal.jetpack.model.repository.interfaces.AuthorCloudRepository
import com.luisansal.jetpack.model.repository.interfaces.AuthorRepository
import com.luisansal.jetpack.model.repository.interfaces.LoginCloudRepository
import com.luisansal.jetpack.model.usecase.AuthorUseCaseImpl
import com.luisansal.jetpack.model.usecase.LoginUseCaseImpl
import com.luisansal.jetpack.model.usecase.UserUseCaseImpl
import com.luisansal.jetpack.model.usecase.interfaces.AuthorUseCase
import com.luisansal.jetpack.model.usecase.interfaces.LoginUseCase
import com.luisansal.jetpack.model.usecase.interfaces.UserUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppBindingModule {

    @Binds
    abstract fun provideUserUseCase(userUseCase: UserUseCaseImpl): UserUseCase

    @Binds
    abstract fun provideAuthorUseCase(authorUseCaseImpl: AuthorUseCaseImpl): AuthorUseCase

    @Binds
    abstract fun provideLoginUseCase(loginUseCaseImpl: LoginUseCaseImpl): LoginUseCase

    @Binds
    abstract fun provideUserRepository(authorRepositoryImpl: AuthorRepositoryImpl): AuthorRepository

    @Binds
    abstract fun provideUserCloudRepository(authorCloudRepositoryImpl: AuthorCloudRepositoryImpl): AuthorCloudRepository

    @Binds
    abstract fun provideLoginCloudRepository(loginCloudRepositoryImpl: LoginCloudRepositoryImpl): LoginCloudRepository
}