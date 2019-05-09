package com.luisansal.jetpack.model.usecase.interfaces

import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.common.observer.BaseSingleObserver

interface AuthorUseCase {
    fun saveAuthor(author: Author,subscriber : BaseCompletableObserver)
    fun getAuthorByDni(dni: String,subscriber : BaseSingleObserver<Author>)
}