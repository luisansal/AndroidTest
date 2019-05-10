package com.luisansal.jetpack.ui.mvp

import com.luisansal.jetpack.common.interfaces.CrudListener
import com.luisansal.jetpack.model.domain.User

interface NewUserFragmentMVP {
    interface View {
        var user : User?
        var crudListener : CrudListener<User>?
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
        fun onClickBtnSiguiente(user: User)
        fun onTextDniChanged(texto : String)
        fun saveUser()
        fun goToNextPage()
    }
}