package com.luisansal.jetpack.ui.mvp.author

import com.luisansal.jetpack.common.exception.AuthorDuplicadoException
import com.luisansal.jetpack.common.executor.PostExecutionThread
import com.luisansal.jetpack.common.executor.ThreadExecutor
import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.model.api.AuthorApi
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.model.usecase.interfaces.AuthorUseCase
import com.luisansal.jetpack.model.usecase.interfaces.UseCase
import javax.inject.Inject

class NewAuthorFragmentPresenter @Inject constructor(private val authorUseCase: AuthorUseCase, private val authorApi: AuthorApi, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread)
    : UseCase(threadExecutor, postExecutionThread), NewAuthorFragmentMVP.Presenter {

    override fun mostrarAuthors() {
        authorUseCase.obtenerTodosAuthors(object : BaseSingleObserver<List<Author>>() {
            override fun onSuccess(t: List<Author>) {
                mView.mostrarAuthors(t)
            }
        })
    }

    override fun restringirGuardadoEnNSegundos(): Boolean {
        mView.seconds?.let { seconds ->
            if (seconds > 0) {
                return false
            }
        }
        return true
    }

    lateinit var mView: NewAuthorFragmentMVP.View

    override fun setView(view: NewAuthorFragmentMVP.View) {
        mView = view
    }

    override fun init() {
        mView.onClickBtnGuardar()
        mView.onClickBtnBuscar()
        mView.onClickBtnMostrar()
//        mView.contadorNSegundos(40)
        mView.setupAdapterAuthors()
        mView.onTextChangedDni()
    }

    override fun guardarAuthor() {
        if (restringirGuardadoEnNSegundos()) {
            mView.notificarRestriccionNSegundos()
        } else {
            mView.author?.let {
                authorUseCase.guardarAuthor(it, GuardarAuthorObserver())
            }
        }
    }

    private inner class GuardarAuthorObserver : BaseCompletableObserver() {
        override fun onComplete() {

            mView.author?.let {
                if (!authorUseCase.comprobarCamposObligatorios(it)) {
                    mView.mostrarErrorCamposObligatorios()
                    return
                }
                if (!authorUseCase.validarDniUsuario(it.dni)) {
                    mView.mostrarErrorDni()
                    return
                }

                mView.notificarGuardado()
            }
        }

        override fun onError(e: Throwable) {
            when (e) {
                is AuthorDuplicadoException -> mView.authorDuplicado(e.message.toString())
            }
        }
    }

    override fun buscarAuthor() {
        mView.dni?.let {
            authorUseCase.buscarAuthorByDni(it, BuscarAuthorObserver())
        }
    }

    inner class BuscarAuthorObserver : BaseSingleObserver<Author>() {
        override fun onSuccess(t: Author) {
            mView.author = t
            mView.authorEncontrado()
        }

        override fun onError(e: Throwable) {
            if (mView.authorNoEncontradoVarias)
                mView.notificarNoEncontrado()
        }
    }

    override fun limpiarCampos() {
        mView.author = Author("", "", "")
        mView.camposVacios()
    }
}