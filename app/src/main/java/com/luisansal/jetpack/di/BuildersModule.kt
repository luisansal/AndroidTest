package com.luisansal.jetpack.di

import com.luisansal.jetpack.MainActivity
import com.luisansal.jetpack.ui.fragments.ListUserFragment
import com.luisansal.jetpack.ui.fragments.NewUserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBindModule::class])
    abstract fun bindListUserFragment(): ListUserFragment


    @ContributesAndroidInjector(modules = [])
    abstract fun bindActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBindModule::class])
    abstract fun bindNewUserFragment(): NewUserFragment

}