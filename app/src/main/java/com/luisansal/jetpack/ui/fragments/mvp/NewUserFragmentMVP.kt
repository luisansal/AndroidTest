package com.luisansal.jetpack.ui.fragments.mvp

import com.luisansal.jetpack.model.domain.User

interface NewUserFragmentMVP {
    interface View {
        fun onClickBtnSiguiente()
        fun onTextDniChanged()
        fun onClickBtnListado()
        fun loadViewModel(user : User)
        fun nextPage()
        fun mostrarResultado(strResultado : String)
    }

    interface Presenter {
        fun setView(view: View)
        fun init()
        fun onClickBtnSiguiente()
        fun onTextDniChanged()
        fun saveUser()
        fun goToNextPage()
    }
}