package com.luisansal.jetpack.model.repository

import android.content.Context
import com.luisansal.jetpack.model.dao.AuthorDao
import com.luisansal.jetpack.model.database.MyRoomDatabase
import com.luisansal.jetpack.model.domain.Author
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AuthorRepository @Inject constructor(mContext: Context) {

    val db = MyRoomDatabase.getDatabase(mContext)
    private var mAuthorDao: AuthorDao? = null

    init {
        if (mAuthorDao == null) {
            mAuthorDao = db!!.authorDao()
        }
    }

    fun save(author: Author): Completable {
        return Completable.create { subscriber ->

            mAuthorDao!!.save(author)

            subscriber.onComplete()
        }
    }

    fun getUserByDni(dni: String): Single<Author> {
        return Single.create {
            it.onSuccess(mAuthorDao!!.findOneByDni(dni))
        }
    }

    fun closeConn() {
        db?.close()
    }

    companion object {
        fun newInstance(mContext: Context): AuthorRepository {
            return AuthorRepository(mContext)
        }
    }
}
