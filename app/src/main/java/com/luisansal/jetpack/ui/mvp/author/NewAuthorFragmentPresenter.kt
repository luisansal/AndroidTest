package com.luisansal.jetpack.ui.mvp.author

import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.model.usecase.interfaces.AuthorUseCase
import com.luisansal.jetpack.ui.fragments.NewAuthorFragment
import com.luisansal.jetpack.ui.mvp.NewAuthorFragmentMVP
import javax.inject.Inject

class NewAuthorFragmentPresenter @Inject constructor(val authorUseCase: AuthorUseCase) : NewAuthorFragmentMVP.Presenter {

    private lateinit var mView: NewAuthorFragmentMVP.View

    override fun setView(view: NewAuthorFragmentMVP.View) {
        mView = view
    }

    override fun init() {
        mView.onClickBtnBuscar()
        mView.onClickBtnGuardar()
    }

    override fun onClickBtnGuardar() {
        mView.author?.let {
            authorUseCase.guardarAuthor(it, object : BaseCompletableObserver() {
                override fun onComplete() {
                    super.onComplete()
                    mView.notificarGuardado()
                }
            })
        }
    }

    override fun onClickBtnBuscar() {
        mView.author?.dni?.let {
            authorUseCase.getAuthorByDni(it,object : BaseSingleObserver<Author>(){
                override fun onSuccess(t: Author) {
                    super.onSuccess(t)
                    mView.cargarCamposEnVista()
                }
            })
        }
    }

    fun guardarAuthor() {

    }

    fun verificarCamposObligatorios() {

    }

}
