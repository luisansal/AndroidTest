package com.luisansal.jetpack.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.luisansal.jetpack.R
import com.luisansal.jetpack.interfaces.ActionsViewPagerListener
import com.luisansal.jetpack.interfaces.CrudListener
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.model.repository.UserRepository

class NewUserFragment : Fragment() {

    var etDni: EditText? = null
    var etNombre: EditText? = null
    var etApellido: EditText? = null

    var btnSiguiente: Button? = null
    var btnListado: Button? = null

    var tvResultado: TextView? = null

    var mUserRepository: UserRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUserRepository = UserRepository(activity!!.application)
        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_user, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etNombre = view.findViewById(R.id.etNombre) as EditText
        etApellido = view.findViewById(R.id.etApellido) as EditText
        etDni = view.findViewById(R.id.etDni) as EditText
        btnSiguiente = view.findViewById(R.id.btnSiguiente) as Button
        btnListado = view.findViewById(R.id.btnListado) as Button
        tvResultado = view.findViewById(R.id.tvResultado) as TextView



        // TODO: Use the ViewModel
        val user = mCrudListener!!.oBject
        if (user != null) {
            tvResultado!!.text = user.name + " " + user.lastName
            etNombre!!.setText(user.name)
            etApellido!!.setText(user.lastName)
        }
        onClickBtnSiguiente()
        onClickBtnListado()
        onTextDniChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActionsViewPagerListener) {
            mActivityListener = context
        } else {
            throw RuntimeException(context.toString()
                    + " must implement " + mActivityListener!!.javaClass.getSimpleName())
        }
    }

    override fun onDetach() {
        super.onDetach()
        mActivityListener = null
    }

    private fun onClickBtnSiguiente() {
        btnSiguiente!!.setOnClickListener {
            val user = User()
            user.name = etNombre!!.text.toString()
            user.lastName = etApellido!!.text.toString()
            user.dni = etDni!!.text.toString()
            mCrudListener!!.oBject = user
            tvResultado!!.text = user.name + " " + user.lastName

            mUserRepository!!.save(user)
            mActivityListener!!.onNext()
        }
    }

    private fun onTextDniChanged() {
        etDni!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                mUserRepository!!.getUserByDni(charSequence.toString()).observe(this@NewUserFragment, Observer { user ->
                    if (user != null) {
                        mCrudListener!!.oBject = user
                        etDni!!.setText(user.dni)
                        etNombre!!.setText(user.name)
                        etApellido!!.setText(user.lastName)
                        tvResultado!!.text = user.name + " " + user.lastName
                    }
                })
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })
    }

    fun onClickBtnListado() {
        btnListado!!.setOnClickListener { mCrudListener!!.onList() }
    }

    companion object {

        var TAG = NewUserFragment::class.java!!.getName()

        private var mActivityListener: ActionsViewPagerListener? = null
        private var mCrudListener: CrudListener<User>? = null


        // TODO: Rename and change types and number of parameters
        fun newInstance(activityListener: ActionsViewPagerListener?, crudListener: CrudListener<User>): NewUserFragment {
            val fragment = NewUserFragment()
            val args = Bundle()
            fragment.arguments = args
            mActivityListener = activityListener
            mCrudListener = crudListener
            return fragment
        }
    }
}// Required empty public constructor
