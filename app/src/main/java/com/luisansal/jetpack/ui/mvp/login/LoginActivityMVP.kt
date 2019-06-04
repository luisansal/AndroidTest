package com.luisansal.jetpack.ui.mvp.login

import com.luisansal.jetpack.model.domain.Login

interface LoginActivityMVP {
    interface View {
        fun onClickBtnLogin()
        var login : Login?
        fun mensajeLoginCorrecto()
        fun mensajeLoginIncorrecto()
    }

    interface Presenter {
        fun setView(view: View)
        fun init()
        fun validarLogin()
        fun onLoginCorrecto()
        fun onLoginIncorrecto()
    }
}