package com.luisansal.jetpack.ui.mvp.author

import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.model.usecase.interfaces.AuthorUseCase
import javax.inject.Inject

class NewAuthorFragmentPresenter @Inject constructor(private val authorUseCase: AuthorUseCase) : NewAuthorFragmentMVP.Presenter {

    lateinit var mView : NewAuthorFragmentMVP.View

    override fun setView(view: NewAuthorFragmentMVP.View) {
        mView = view
    }

    override fun init() {
        mView.onClickBtnGuardar()
    }

    override fun guardarAuthor() {
        mView.author?.let {
            authorUseCase.guardarAuthor(it, object : BaseCompletableObserver(){
                override fun onComplete() {
                    mView.notificarGuardado()
                }
            })
        }
    }

    override fun buscarAuthor() {

    }

    override fun limpiarCampos() {

    }

}