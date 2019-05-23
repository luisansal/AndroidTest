package com.luisansal.jetpack.ui.mvp.author

import com.luisansal.jetpack.common.exception.AuthorDuplicadoException
import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.model.usecase.interfaces.AuthorUseCase
import javax.inject.Inject

class NewAuthorFragmentPresenter @Inject constructor(private val authorUseCase: AuthorUseCase) : NewAuthorFragmentMVP.Presenter {

    lateinit var mView: NewAuthorFragmentMVP.View

    override fun setView(view: NewAuthorFragmentMVP.View) {
        mView = view
    }

    override fun init() {
        mView.onClickBtnGuardar()
        mView.onClickBtnBuscar()
    }

    override fun guardarAuthor() {
        mView.author?.let {
            authorUseCase.guardarAuthor(it, GuardarAuthorObserver() )
        }
    }

    inner class GuardarAuthorObserver : BaseCompletableObserver() {
        override fun onComplete() {

            mView.author?.let{
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
            when(e){
                is AuthorDuplicadoException -> mView.authorDuplicado(e.message.toString())
            }

        }
    }

    override fun buscarAuthor() {
        mView.dni?.let {
            authorUseCase.buscarAuthorByDni(it, object : BaseSingleObserver<Author>() {
                override fun onSuccess(t: Author) {
                    mView.author = t
                    mView.authorEncontrado()
                }

                override fun onError(e: Throwable) {
                    mView.notificarNoEncontrado()
                }
            })
        }
    }

    override fun limpiarCampos() {
        mView.author = Author("", "", "")
        mView.camposVacios()
    }
}