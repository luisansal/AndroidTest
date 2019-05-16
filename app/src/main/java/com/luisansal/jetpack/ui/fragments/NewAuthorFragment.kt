package com.luisansal.jetpack.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.luisansal.jetpack.R
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.ui.mvp.author.NewAuthorFragmentMVP
import com.luisansal.jetpack.ui.mvp.author.NewAuthorFragmentPresenter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_new_author.*
import javax.inject.Inject

class NewAuthorFragment : Fragment(), NewAuthorFragmentMVP.View{

    @Inject
    lateinit var mPresenter : NewAuthorFragmentPresenter

    override fun onClickBtnGuardar() {
        btnGuardar.setOnClickListener {
            author = Author(etDni.text.toString(),etNombre.text.toString(),etApellido.text.toString())
            mPresenter.guardarAuthor()
        }
    }

    override fun onClickBtnBuscar() {
        btnBuscar.setOnClickListener {
            dni = etDni.text.toString()
            mPresenter.buscarAuthor()
        }
    }

    override fun notificarGuardado() {
        Toast.makeText(context,"Author ${author?.nombre} ${author?.apellido} Guardado",Toast.LENGTH_LONG).show()
    }

    override fun cargarCamposEnVista() {
        etNombre.setText(author?.nombre)
        etApellido.setText(author?.apellido)
    }

    override fun authorEncontrado() {
        cargarCamposEnVista()
        notificarEncontrado()
    }

    override fun notificarEncontrado() {
        Toast.makeText(context,"Author ${author?.nombre} ${author?.apellido} Encontrado",Toast.LENGTH_LONG).show()
    }

    override fun camposVacios() {
        etDni.setText(author?.dni)
        etNombre.setText(author?.nombre)
        etApellido.setText(author?.apellido)
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