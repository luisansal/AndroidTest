package com.luisansal.jetpack.model.usecase

import com.luisansal.jetpack.common.executor.PostExecutionThread
import com.luisansal.jetpack.common.executor.ThreadExecutor
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.model.database.MyRoomDatabase
import com.luisansal.jetpack.model.repository.interfaces.AuthorRepository
import com.luisansal.jetpack.model.usecase.interfaces.AuthorUseCase
import com.luisansal.jetpack.model.usecase.interfaces.UseCase
import com.luisansal.jetpack.model.validation.AuthorValidation
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AuthorUseCaseImpl @Inject constructor(private val authorRepository: AuthorRepository, threadExecutor: ThreadExecutor,
                                            postExecutionThread: PostExecutionThread) : UseCase(threadExecutor, postExecutionThread)
        , AuthorUseCase {

    override var db: MyRoomDatabase? = authorRepository.db

    override fun validarDniUsuario(dni: String): Boolean {
        return false
    }

    override fun comprobarCamposObligatorios(author: Author): Boolean {
        return false
    }

    override fun guardarAuthor(author: Author, subscriber: BaseCompletableObserver): Completable {
        val completable = authorRepository.guardarAuthor(author)
        return execute(completable, subscriber)
    }

    override fun buscarAuthorByDni(dni: String, subscriber: BaseSingleObserver<Author>): Single<Author> {
        val single = authorRepository.buscarAuthorByDni(dni)
        return execute(single, subscriber)
    }
}