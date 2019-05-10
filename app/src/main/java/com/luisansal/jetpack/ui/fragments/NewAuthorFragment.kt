package com.luisansal.jetpack.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.luisansal.jetpack.R
import com.luisansal.jetpack.common.interfaces.CrudListener
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.ui.mvp.NewAuthorFragmentMVP
import com.luisansal.jetpack.ui.mvp.author.NewAuthorFragmentPresenter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_new_author.*
import javax.inject.Inject

class NewAuthorFragment : Fragment(), NewAuthorFragmentMVP.View {
    override fun cargarCamposEnVista() {
        etNombre.setText(author?.name)
        etApellido.setText(author?.lastName)
    }

    override fun onClickBtnGuardar() {
        btnGuardar.setOnClickListener {
            presenter.onClickBtnBuscar()
        }
    }

    override fun onClickBtnBuscar() {
        btnBuscar.setOnClickListener {
            presenter.onClickBtnBuscar()
        }
    }

    override fun notificarGuardado() {
        Toast.makeText(context,"Author Guardado",Toast.LENGTH_LONG).show()
    }

    override var author: Author? = null

    @Inject
    lateinit var presenter : NewAuthorFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        presenter.init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_new_author, container, false)
        return view
    }

    companion object {

        var TAG = NewAuthorFragment::class.java.getName()

        fun newInstance(crudListener: CrudListener<User>): NewAuthorFragment {
            val fragment = NewAuthorFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}