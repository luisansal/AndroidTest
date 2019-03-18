package com.luisansal.jetpack.di

import com.luisansal.jetpack.model.repository.UserRepository
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentInteractor
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentMVP
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentPresenter
import dagger.Module
import dagger.Provides

@Module
class ListUserFragmentModule {

    @Provides
    fun providePresenter(listUserFragmentView: ListUserFragmentMVP.View, listUserFragmentInteractor: ListUserFragmentMVP.Interactor): ListUserFragmentMVP.Presenter{
        return ListUserFragmentPresenter(listUserFragmentView, listUserFragmentInteractor)
    }

    @Provides
    fun provideInteractor(userRepository: UserRepository): ListUserFragmentMVP.Interactor{
        return ListUserFragmentInteractor(userRepository)
    }

}