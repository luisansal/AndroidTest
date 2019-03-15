package com.luisansal.jetpack.di

import androidx.lifecycle.ViewModelProviders
import com.luisansal.jetpack.model.MyApplication
import com.luisansal.jetpack.model.repository.UserRepository
import com.luisansal.jetpack.ui.fragments.ListUserFragment
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentInteractor
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentMVP
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentPresenter
import com.luisansal.jetpack.ui.viewmodel.RoomViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RoomModule{

    @Provides
    @Singleton
    fun provideUserRepository(application :  MyApplication) : UserRepository{
        return UserRepository.newInstance(application.applicationContext)
    }


    @Provides
    @Singleton
    fun provideView() : ListUserFragment{
        return ListUserFragment()
    }

//    @Provides
//    @Singleton
//    fun provideRoomViewModel(view: ListUserFragment) : RoomViewModel{
//        val mRoomViewModel = ViewModelProviders.of(view).get(RoomViewModel::class.java)
//        return mRoomViewModel
//    }

    @Provides
    @Singleton
    fun providePresenter(view : ListUserFragment) : ListUserFragmentPresenter{
        return ListUserFragmentPresenter(view)
    }
//
//    @Provides
//    @Singleton
//    fun provideInteractor(presenter : ListUserFragmentMVP.Presenter) : ListUserFragmentMVP.Interactor{
//        return ListUserFragmentInteractor(presenter)
//    }

}