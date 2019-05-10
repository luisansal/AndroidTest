package com.luisansal.jetpack.ui.mvp

import com.luisansal.jetpack.model.domain.Author

class NewAuthorFragmentMVP {

    interface View{
        fun onClickBtnGuardar()
        fun onClickBtnBuscar()
        fun notificarGuardado()

        var author : Author?
    }

    interface Presenter{
        fun setView(view:View)
        fun init()
        fun onClickBtnGuardar()
        fun onClickBtnBuscar()
    }

}
