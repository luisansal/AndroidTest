package com.luisansal.jetpack.ui.mvp.user

import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.model.usecase.interfaces.UserUseCase
import com.luisansal.jetpack.ui.fragments.NewUserFragment
import javax.inject.Inject

class NewUserFragmentPresenter @Inject constructor(private val userUseCase: UserUseCase) : NewUserFragmentMVP.Presenter {

    private lateinit var mView: NewUserFragmentMVP.View

    override fun onClickBtnSiguiente(user: User) {
        mView.crudListener?.oBject = user

        mView.mostrarResultado(user.nombre + " " + user.apellido)
    }

    override fun onTextDniChanged(texto: String) {
        userUseCase.getUserByDni(texto, TextChangedSubscriber())
    }

    override fun init() {
        mView.onClickBtnListado()
        mView.onClickBtnSiguiente()
        mView.onTextDniChanged()
        mView.crudListener?.oBject?.let { mView.loadViewModel(it) }
    }


    override fun setView(view: NewUserFragmentMVP.View) {
        mView = view as NewUserFragment
    }


    override fun saveUser() {
        mView.crudListener?.oBject?.let { userUseCase.saveUser(it, SaveUserSubscriber()) }
    }

    override fun goToNextPage() {

    }

    private inner class TextChangedSubscriber : BaseSingleObserver<User>() {
        override fun onSuccess(t: User) {
            mView.crudListener?.oBject = t
            mView.loadViewModel(t)
        }
    }

    private inner class SaveUserSubscriber : BaseCompletableObserver() {
        override fun onComplete() {
            mView.nextPage()
        }
    }
}