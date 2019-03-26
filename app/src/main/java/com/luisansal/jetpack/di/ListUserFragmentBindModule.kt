package com.luisansal.jetpack.di

import com.luisansal.jetpack.ui.fragments.ListUserFragment
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentInteractor
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentMVP
import dagger.Binds
import dagger.Module


@Module
abstract class ListUserFragmentBindModule {

    @Binds
    abstract fun provideView(listUserFragment: ListUserFragment): ListUserFragmentMVP.View

    @Binds
    abstract fun bindInteractor(listUserFragmentInteractor: ListUserFragmentInteractor): ListUserFragmentMVP.Interactor

}


