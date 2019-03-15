package com.luisansal.jetpack.ui.viewmodel

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

import com.luisansal.jetpack.model.domain.User

class RoomViewModel : ViewModel() {

    private val TAG = this.javaClass.getName()
    private var mUser: User? = null

    lateinit var users: LiveData<PagedList<User>>

    var user: User?
        get() {
            Log.i(TAG, "get User")
            return mUser
        }
        set(user) {
            Log.i(TAG, "set User")
            mUser = user
        }
}
