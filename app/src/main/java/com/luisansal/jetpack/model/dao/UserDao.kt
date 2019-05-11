package com.luisansal.jetpack.model.dao

import com.luisansal.jetpack.model.domain.User

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: User): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(users: List<User>)

    @Query("DELETE FROM tbluser")
    fun deleteAll()

    @Query("SELECT * from tbluser ORDER BY nombre ASC")
    fun findAllUsers(): LiveData<List<User>>

    @Query("SELECT * from tbluser ORDER BY nombre ASC")
    fun findAllUsersInline(): List<User>

    // The Integer type parameter tells Room to use a PositionalDataSource
    // object, with position-based loading under the hood.
    @Query("SELECT * FROM tbluser ORDER BY nombre asc")
    fun findAllUsersPaging(): DataSource.Factory<Int, User>


    @Query("SELECT * from tbluser where dni = :dni")
    fun findOneByDni(dni: String): User
}
