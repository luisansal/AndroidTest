package com.luisansal.jetpack.di

import com.luisansal.jetpack.MainActivity
import com.luisansal.jetpack.ui.fragments.ListUserFragment
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentMVP
import dagger.Binds
import dagger.Module


@Module
abstract class ListUserFragmentViewModule {

    @Binds
    abstract fun provideView(listUserFragment: ListUserFragment): ListUserFragmentMVP.View

}


