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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(authors: List<Author>)

    @Query("SELECT * from tblauthor where dni = :dni")
    fun findOneByDni(dni: String): Author

    @Query("SELECT * from tblauthor")
    fun findAllAuthors(): List<Author>
}
