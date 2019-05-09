package com.luisansal.jetpack.model.repository

import android.content.Context

import com.luisansal.jetpack.model.dao.UserDao
import com.luisansal.jetpack.model.database.MyRoomDatabase
import com.luisansal.jetpack.model.domain.User

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(mContext: Context) {

    val db = MyRoomDatabase.getDatabase(mContext)

    val allUsers: LiveData<List<User>>
        get() = mUserDaoInstance!!.findAllUsers()

    val allUsersInline: List<User>
        get() = mUserDaoInstance!!.findAllUsersInline()

    val allUsersPaging: DataSource.Factory<Int, User>
        get() = mUserDaoInstance!!.findAllUsersPaging()

    init {

        if (mUserDaoInstance == null) {
            mUserDaoInstance = db!!.userDao()
        }
    }

    fun save(user: User): Completable {
        return Completable.create { subscriber ->

            mUserDaoInstance!!.save(user)

            subscriber.onComplete()
        }
    }

    fun saveAll(users: List<User>) {
        mUserDaoInstance!!.saveAll(users)
    }

    fun deleteAll() {
        mUserDaoInstance!!.deleteAll()
    }

    fun getUserByDni(dni: String): Single<User> {
        return Single.create {
            it.onSuccess(mUserDaoInstance!!.findOneByDni(dni))
        }
    }

    fun closeConn() {
        db?.close()
    }

    companion object {
        private var mUserDaoInstance: UserDao? = null

        fun newInstance(mContext: Context): UserRepository {
            return UserRepository(mContext)
        }
    }
}
