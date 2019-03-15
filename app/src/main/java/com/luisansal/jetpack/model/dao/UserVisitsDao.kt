package com.luisansal.jetpack.model.dao

import com.luisansal.jetpack.model.domain.UserAndAllVists

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserVisitsDao {
    @Query("SELECT * FROM tbluser WHERE id = :id")
    fun findUserAllVisitsById(id: Long?): LiveData<UserAndAllVists>

    @Query("SELECT * FROM tbluser WHERE dni = :dni")
    fun findUserAllVisitsByDni(dni: String): LiveData<UserAndAllVists>
}
