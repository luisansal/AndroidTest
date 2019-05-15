package com.luisansal.jetpack.di

import com.luisansal.jetpack.ui.fragments.ListUserFragment
import com.luisansal.jetpack.ui.fragments.NewUserFragment
import com.luisansal.jetpack.ui.mvp.user.ListUserFragmentInteractor
import com.luisansal.jetpack.ui.mvp.user.ListUserFragmentMVP
import com.luisansal.jetpack.ui.mvp.user.NewUserFragmentMVP
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
}