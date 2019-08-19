package com.luisansal.jetpack.ui.mvp.author

import com.luisansal.jetpack.model.domain.Author

interface NewAuthorFragmentMVP {

    interface View{
        fun onClickBtnGuardar()
        fun onClickBtnBuscar()
        fun onClickBtnMostrar()
        fun onTextChangedDni()
        fun notificarGuardado()
        fun cargarCamposEnVista()
        fun authorEncontrado()
        fun notificarEncontrado()
        fun camposVacios()
        fun mostrarErrorDni()
        fun mostrarErrorCamposObligatorios()
        var author : Author?
        var dni : String?
        var seconds : Int?
        var authorNoEncontradoVarias : Boolean
        fun notificarNoEncontrado()
        fun authorDuplicado(mensaje : String)
        fun contadorNSegundos(n : Int)
        fun notificarRestriccionNSegundos()
        fun setupAdapterAuthors()
        fun mostrarAuthors(authors: List<Author>)
        fun ocultarcontador()
    }

    interface Presenter{
        fun setView(view: View)
        fun init()
        fun guardarAuthor()
        fun buscarAuthor()
        fun limpiarCampos()
        fun restringirGuardadoEnNSegundos() : Boolean
        fun mostrarAuthors()
    }

}