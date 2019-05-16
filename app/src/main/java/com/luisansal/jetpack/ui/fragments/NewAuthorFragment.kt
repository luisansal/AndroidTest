package com.luisansal.jetpack.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.luisansal.jetpack.R
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.ui.mvp.author.NewAuthorFragmentMVP
import dagger.android.support.AndroidSupportInjection

class NewAuthorFragment : Fragment(), NewAuthorFragmentMVP.View{


    override fun onClickBtnGuardar() {

    }

    override fun onClickBtnBuscar() {

    }

    override fun notificarGuardado() {

    }

    override fun cargarCamposEnVista() {

    }

    override fun authorEncontrado() {

    }

    override fun notificarEncontrado() {

    }

    override fun camposVacios() {

    }

    override fun mostrarErrorDni() {

    }

    override fun mostrarErrorCamposObligatorios() {

    }

    override var author: Author? = null

    override var dni: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_new_author, container, false)
        return view
    }

    companion object {

        var TAG = NewAuthorFragment::class.java.getName()

        fun newInstance(): NewAuthorFragment {
            val fragment = NewAuthorFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}