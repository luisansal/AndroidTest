package com.luisansal.jetpack.model.repository

import android.app.Application

import com.luisansal.jetpack.model.dao.UserVisitsDao
import com.luisansal.jetpack.model.database.MyRoomDatabase
import com.luisansal.jetpack.model.domain.UserAndAllVists

import androidx.lifecycle.LiveData

class UserVisitsRepository(application: Application) {

    init {
        val db = MyRoomDatabase.getDatabase(application)
        if (mUserDaoInstance == null) {
            mUserDaoInstance = db!!.userVisitsDao()
        }
    }

    fun getUserAllVisitsById(userId: Long?): LiveData<UserAndAllVists> {
        return mUserDaoInstance!!.findUserAllVisitsById(userId)
    }

    fun getUserAllVisitsByDni(dni: String): LiveData<UserAndAllVists> {
        return mUserDaoInstance!!.findUserAllVisitsByDni(dni)
    }

    companion object {
        private var mUserDaoInstance: UserVisitsDao? = null

        fun newInstance(application: Application): UserVisitsRepository {
            return UserVisitsRepository(application)
        }
    }

    //    public void save(UserAndAllVists user) {
    //        mUserDaoInstance.save(user);
    //    }

}
