package com.luisansal.jetpack.ui.mvp

import com.luisansal.jetpack.model.domain.Author

class NewAuthorFragmentMVP {

    interface View{
        fun onClickBtnGuardar()
        fun onClickBtnBuscar()
        fun notificarGuardado()
        fun cargarCamposEnVista()
        fun authorEncontrado()
        fun notificarEncontrado()
        fun camposVacios()
        var author : Author?
        var dni : String?
    }

    interface Presenter{
        fun setView(view:View)
        fun init()
        fun guardarAuthor()
        fun buscarAuthor()
        fun limpiarCampos()
    }

}
