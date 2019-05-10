package com.luisansal.jetpack.model.usecase

import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.model.repository.AuthorRepository
import com.luisansal.jetpack.model.usecase.interfaces.AuthorUseCase
import com.luisansal.jetpack.model.usecase.interfaces.UseCase
import javax.inject.Inject

class AuthorUseCaseImpl @Inject constructor(private val authorRepository: AuthorRepository) : UseCase(), AuthorUseCase {
    override fun guardarAuthor(author: Author, subscriber: BaseCompletableObserver) {
        val completable = authorRepository.saveAuthor(author)
        execute(completable, subscriber)
    }

    override fun getAuthorByDni(dni: String, subscriber: BaseSingleObserver<Author>) {
        val single = authorRepository.getAuthorByDni(dni)
        execute(single, subscriber)
    }
}