package com.luisansal.jetpack.model.usecase.interfaces

import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.model.database.MyRoomDatabase
import io.reactivex.Completable
import io.reactivex.Single

interface AuthorUseCase {
    fun guardarAuthor(author: Author, subscriber : BaseCompletableObserver) : Completable
    fun buscarAuthorByDni(dni: String, subscriber : BaseSingleObserver<Author>) : Single<Author>
    fun comprobarCamposObligatorios(author: Author) : Boolean
    fun validarDniUsuario(dni : String) : Boolean
    val db : MyRoomDatabase?
}