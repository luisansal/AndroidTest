package com.luisansal.jetpack.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.luisansal.jetpack.model.domain.Author

@Dao
interface AuthorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(author: Author): Long


    @Query("SELECT * from tblauthor where dni = :dni")
    fun findOneByDni(dni: String): Author
}
