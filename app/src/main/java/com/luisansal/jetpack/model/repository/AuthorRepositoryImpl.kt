package com.luisansal.jetpack.model.repository

import android.content.Context
import com.luisansal.jetpack.model.dao.AuthorDao
import com.luisansal.jetpack.model.database.MyRoomDatabase
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.model.repository.interfaces.AuthorRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AuthorRepositoryImpl @Inject constructor(mContext: Context) : AuthorRepository {
    override fun guardarAuthor(author: Author): Completable {
        return Completable.create {
            mAuthorDao!!.save(author)
            it.onComplete()
        }
    }

    override fun buscarAuthorByDni(dni: String): Single<Author> {
        return Single.create {
            it.onSuccess(mAuthorDao!!.findOneByDni(dni))
        }
    }

    override var db = MyRoomDatabase.getDatabase(mContext)

    private var mAuthorDao: AuthorDao? = null

    init {
        if (!db?.isOpen!!) {
            db = MyRoomDatabase.getDatabase(mContext, true)
        }
        mAuthorDao = db!!.authorDao()
    }
}
