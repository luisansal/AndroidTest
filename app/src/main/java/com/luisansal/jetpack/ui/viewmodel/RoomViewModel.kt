package com.luisansal.jetpack.ui.viewmodel

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

import com.luisansal.jetpack.model.domain.User

class RoomViewModel : ViewModel() {

    private val TAG = this.javaClass.getName()

    lateinit var users: LiveData<PagedList<User>>

    var user: User? = null
        get() {
            Log.i(TAG, "get User")
            return field
        }
        set(user) {
            Log.i(TAG, "set User")
            field = user
        }
}
