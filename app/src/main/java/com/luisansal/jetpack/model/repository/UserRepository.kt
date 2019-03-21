package com.luisansal.jetpack.model.repository

import android.content.Context

import com.luisansal.jetpack.model.dao.UserDao
import com.luisansal.jetpack.model.database.MyRoomDatabase
import com.luisansal.jetpack.model.domain.User

import androidx.lifecycle.LiveData
import androidx.paging.DataSource

class UserRepository(mContext: Context) {

    val allUsers: LiveData<List<User>>
        get() = mUserDaoInstance!!.findAllUsers()

    val allUsersInline: List<User>
        get() = mUserDaoInstance!!.findAllUsersInline()

    val allUsersPaging: DataSource.Factory<Int, User>
        get() = mUserDaoInstance!!.findAllUsersPaging()

    init {
        val db = MyRoomDatabase.getDatabase(mContext)
        if (mUserDaoInstance == null) {
            mUserDaoInstance = db!!.userDao()
        }
    }

    fun save(user: User) {
        mUserDaoInstance!!.save(user)
    }

    fun saveAll(users: List<User>) {
        mUserDaoInstance!!.saveAll(users)
    }

    fun getUserByDni(dni: String): LiveData<User> {
        return mUserDaoInstance!!.findOneByDni(dni)
    }

    companion object {
        private var mUserDaoInstance: UserDao? = null

        fun newInstance(mContext: Context): UserRepository {
            return UserRepository(mContext)
        }
    }
}
