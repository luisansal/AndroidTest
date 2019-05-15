package com.luisansal.jetpack.model.repository.interfaces

import com.luisansal.jetpack.model.database.MyRoomDatabase
import com.luisansal.jetpack.model.domain.Author
import io.reactivex.Completable
import io.reactivex.Single

interface AuthorRepository {

    fun guardarAuthor(author: Author) : Completable
    fun buscarAuthorByDni(dni : String) : Single<Author>
    var db : MyRoomDatabase?

}