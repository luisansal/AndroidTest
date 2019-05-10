package com.luisansal.jetpack.di

import com.luisansal.jetpack.model.usecase.AuthorUseCaseImpl
import com.luisansal.jetpack.model.usecase.UserUseCaseImpl
import com.luisansal.jetpack.model.usecase.interfaces.AuthorUseCase
import com.luisansal.jetpack.model.usecase.interfaces.UserUseCase
import com.luisansal.jetpack.ui.fragments.ListUserFragment
import com.luisansal.jetpack.ui.fragments.NewAuthorFragment
import com.luisansal.jetpack.ui.fragments.NewUserFragment
import com.luisansal.jetpack.ui.mvp.ListUserFragmentInteractor
import com.luisansal.jetpack.ui.mvp.ListUserFragmentMVP
import com.luisansal.jetpack.ui.mvp.NewAuthorFragmentMVP
import com.luisansal.jetpack.ui.mvp.NewUserFragmentMVP
import dagger.Binds
import dagger.Module


@Module
abstract class FragmentBindModule {

    @Binds
    abstract fun provideView(listUserFragment: ListUserFragment): ListUserFragmentMVP.View

    @Binds
    abstract fun bindInteractor(listUserFragmentInteractor: ListUserFragmentInteractor): ListUserFragmentMVP.Interactor

    @Binds
    abstract fun provideNewUserView(newUserFragment: NewUserFragment): NewUserFragmentMVP.View

    @Binds
    abstract fun provideUserUseCase(userUseCase: UserUseCaseImpl): UserUseCase

    @Binds
    abstract fun provideAuthorView(newAuthorFragment: NewAuthorFragment): NewAuthorFragmentMVP.View

    @Binds
    abstract fun provideAuthorUseCase(authorUseCaseImpl: AuthorUseCaseImpl): AuthorUseCase
}


