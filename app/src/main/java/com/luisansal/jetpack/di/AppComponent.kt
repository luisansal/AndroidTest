package com.luisansal.jetpack.di

import com.luisansal.jetpack.MainActivity
import com.luisansal.jetpack.ui.fragments.ListUserFragment
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentInteractor
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(listUserFragment: ListUserFragment)
}