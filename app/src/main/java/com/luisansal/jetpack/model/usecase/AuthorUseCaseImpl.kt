package com.luisansal.jetpack.model.usecase

import com.luisansal.jetpack.common.exception.AuthorDuplicadoException
import com.luisansal.jetpack.common.executor.PostExecutionThread
import com.luisansal.jetpack.common.executor.ThreadExecutor
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.model.repository.interfaces.AuthorCloudRepository
import com.luisansal.jetpack.model.repository.interfaces.AuthorRepository
import com.luisansal.jetpack.model.usecase.interfaces.AuthorUseCase
import com.luisansal.jetpack.model.usecase.interfaces.UseCase
import com.luisansal.jetpack.model.validation.AuthorValidation
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AuthorUseCaseImpl @Inject constructor(private val authorRepository: AuthorRepository, threadExecutor: ThreadExecutor,
                                            private val authorCloudRepository: AuthorCloudRepository,
                                            postExecutionThread: PostExecutionThread) : UseCase(threadExecutor, postExecutionThread)
        , AuthorUseCase {

    override fun obtenerTodosAuthors(subscriber: BaseSingleObserver<List<Author>>): Single<List<Author>> {
        val single = authorRepository.todosAuthors()
        return execute(single, subscriber)
    }

    override fun guardarAuthorsEnLocal() {
        val single = authorCloudRepository.getAuthors()

        execute(single, object : BaseSingleObserver<List<Author>>(){
            override fun onSuccess(t: List<Author>) {
                execute(authorRepository.guardarAuthors(t),BaseCompletableObserver())
            }
        })
    }

    override fun validarAuthorDuplicado(dni: String): Boolean {
        val author = authorRepository.buscarAuthorDuplicadoByDni(dni)
        if (author != null)
            return true
        return false
    }

    override fun validarDniUsuario(dni: String): Boolean {
        return AuthorValidation.dniCorrecto(dni)
    }

    override fun comprobarCamposObligatorios(author: Author): Boolean {
        return AuthorValidation.comprobarCamposObligatorios(author)
    }

    override fun guardarAuthor(author: Author, subscriber: BaseCompletableObserver): Completable {
        var completable = authorRepository.guardarAuthor(author)
        if (validarAuthorDuplicado(author.dni)) {
            completable = Completable.error(AuthorDuplicadoException(author))
        }
        return execute(completable, subscriber)
    }

    override fun buscarAuthorByDni(dni: String, subscriber: BaseSingleObserver<Author>): Single<Author> {
        val single = authorRepository.buscarAuthorByDni(dni)
        return execute(single, subscriber)
    }
}