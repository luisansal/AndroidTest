package com.luisansal.jetpack.ui.mvp.login

import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.model.domain.Login
import com.luisansal.jetpack.model.usecase.interfaces.LoginUseCase
import javax.inject.Inject

class LoginActivityPresenter @Inject constructor(private val loginUseCase: LoginUseCase) : LoginActivityMVP.Presenter {

    lateinit var mView: LoginActivityMVP.View

    override fun setView(view: LoginActivityMVP.View) {
        mView = view
    }

    override fun init() {
        mView.onClickBtnLogin()
    }

    override fun validarLogin() {
        mView.login?.let {
            loginUseCase.login(it.usr, it.pwd, object : BaseSingleObserver<Login>() {
                override fun onSuccess(t: Login) {
                   onLoginCorrecto()
                }

                override fun onError(e: Throwable) {
                  onLoginIncorrecto()
                }
            })
        }
    }

    override fun onLoginCorrecto() {
        mView.mensajeLoginCorrecto()
        mView.entrarPantallaPrincipal()
    }

    override fun onLoginIncorrecto() {
        mView.mensajeLoginIncorrecto()
    }
}