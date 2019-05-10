package com.luisansal.jetpack.ui.mvp.author

import com.luisansal.jetpack.model.usecase.interfaces.AuthorUseCase
import com.luisansal.jetpack.ui.mvp.NewAuthorFragmentMVP
import javax.inject.Inject

class NewAuthorFragmentPresenter @Inject constructor(val authorUseCase: AuthorUseCase) : NewAuthorFragmentMVP.Presenter {
    override fun setView(view: NewAuthorFragmentMVP.View) {

    }

    override fun init() {

    }

    override fun onClickBtnGuardar() {

    }

    override fun onClickBtnBuscar() {

    }

    fun guardarAuthor() {

    }

    fun verificarCamposObligatorios() {

    }

}
