package com.luisansal.jetpack.di

import com.luisansal.jetpack.MainActivity
import com.luisansal.jetpack.ui.fragments.ListUserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [ListUserFragmentBindModule::class])
    abstract fun bindListUserFragment(): ListUserFragment


    @ContributesAndroidInjector(modules = [])
    abstract fun bindActivity(): MainActivity

}