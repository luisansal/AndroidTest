package com.luisansal.jetpack.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.luisansal.jetpack.R
import com.luisansal.jetpack.common.interfaces.CrudListener
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.ui.mvp.NewAuthorFragmentMVP
import com.luisansal.jetpack.ui.mvp.author.NewAuthorFragmentPresenter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_new_author.view.*
import kotlinx.android.synthetic.main.fragment_new_user.view.*
import kotlinx.android.synthetic.main.fragment_new_user.view.etApellido
import kotlinx.android.synthetic.main.fragment_new_user.view.etDni
import kotlinx.android.synthetic.main.fragment_new_user.view.etNombre
import javax.inject.Inject

class NewAuthorFragment : Fragment(), NewAuthorFragmentMVP.View {
    override fun onClickBtnGuardar() {
        btnGuardar.setOnClickListener {
            presenter.onClickBtnBuscar()
        }
    }

    override fun onClickBtnBuscar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun notificarGuardado() {
        Toast.makeText(context,"Author Guardado",Toast.LENGTH_LONG).show()
    }

    override var author: Author? = null

    lateinit var etDni : EditText
    lateinit var etNombre : EditText
    lateinit var etApellido : EditText

    lateinit var btnGuardar : Button
    lateinit var btnBuscar : Button

    @Inject
    lateinit var presenter : NewAuthorFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_author, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etDni = view.etDni
        etNombre = view.etNombre
        etApellido = view.etApellido
        btnBuscar = view.btnBuscar
        btnGuardar = view.btnguardar
    }

    companion object {

        var TAG = NewAuthorFragment::class.java.getName()

        // TODO: Rename and change types and number of parameters
        fun newInstance(crudListener: CrudListener<User>): NewAuthorFragment {
            val fragment = NewAuthorFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}