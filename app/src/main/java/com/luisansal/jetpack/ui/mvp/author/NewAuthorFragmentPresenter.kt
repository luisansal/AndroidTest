package com.luisansal.jetpack.ui.mvp.author

import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.model.usecase.interfaces.AuthorUseCase
import javax.inject.Inject

class NewAuthorFragmentPresenter @Inject constructor(val authorUseCase: AuthorUseCase) : NewAuthorFragmentMVP.Presenter {
    override fun limpiarCampos() {
        mView.author = Author("", "", "")
        mView.camposVacios()
    }

    override fun buscarAuthor() {
        mView.dni?.let {
            authorUseCase.getAuthorByDni(it, object : BaseSingleObserver<Author>() {
                override fun onSuccess(t: Author) {
                    mView.author = t
                    mView.authorEncontrado()
                }
            })
        }
    }

    private lateinit var mView: NewAuthorFragmentMVP.View

    override fun setView(view: NewAuthorFragmentMVP.View) {
        mView = view
    }

    override fun init() {
        mView.onClickBtnBuscar()
        mView.onClickBtnGuardar()
    }

    override fun guardarAuthor() {
        mView.author?.let {
            var validacionCorrecta = true
            if (!authorUseCase.comprobarCamposObligatorios(it)) {
                validacionCorrecta = false
                mView.mostrarErrorCamposObligatorios()
            }
            if (!authorUseCase.validarDniUsuario(it.dni)) {
                validacionCorrecta = false
                mView.mostrarErrorDni()
            }

            if (validacionCorrecta)
                authorUseCase.guardarAuthor(it, object : BaseCompletableObserver() {
                    override fun onComplete() {
                        super.onComplete()
                        mView.notificarGuardado()
                    }
                })
        }
    }

}
