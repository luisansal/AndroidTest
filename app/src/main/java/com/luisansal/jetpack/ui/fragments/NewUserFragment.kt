package com.luisansal.jetpack.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.luisansal.jetpack.R
import com.luisansal.jetpack.common.interfaces.ActionsViewPagerListener
import com.luisansal.jetpack.common.interfaces.CrudListener
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.ui.mvp.NewUserFragmentMVP
import com.luisansal.jetpack.ui.mvp.NewUserFragmentPresenter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_new_user.*
import javax.inject.Inject

class NewUserFragment : Fragment(), NewUserFragmentMVP.View {
    override var user: User? = null

    override var crudListener: CrudListener<User> ? = null

    @Inject
    lateinit var mPresenter: NewUserFragmentPresenter

    var mActivityListener: ActionsViewPagerListener? = null
    lateinit var mCrudListener: CrudListener<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_user, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter.setView(this)
        mPresenter.init()
    }

    override fun mostrarResultado(strResult: String) {
        tvResultado.text = strResult
    }

    override fun loadViewModel(user: User) {
        mostrarResultado(user.nombre + " " + user.apellido)
        etNombre.setText(user.nombre)
        etApellido.setText(user.apellido)
    }

    override fun nextPage() {
        mActivityListener!!.onNext()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            mActivityListener = context as ActionsViewPagerListener
        } catch (e : Exception) {
            throw RuntimeException(context.toString()
                    + " must implement " + mActivityListener!!.javaClass.getSimpleName())
        }
    }

    override fun onDetach() {
        super.onDetach()
        mActivityListener = null
    }

    override fun onClickBtnSiguiente() {
        btnSiguiente.setOnClickListener {
            user = User(etDni.text.toString(),etNombre.text.toString(),etApellido.text.toString())
            mPresenter.onClickBtnSiguiente(user!!)
        }
    }

    override fun onTextDniChanged() {
        etDni.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mPresenter.onTextDniChanged(s.toString())
            }

        })
    }

    override fun onClickBtnListado() {
        btnListado.setOnClickListener { mCrudListener.onList() }
    }

    companion object {

        var TAG = NewUserFragment::class.java.getName()

        // TODO: Rename and change types and number of parameters
        fun newInstance(crudListener: CrudListener<User>): NewUserFragment {
            val fragment = NewUserFragment()
            val args = Bundle()
            fragment.arguments = args
            fragment.mCrudListener = crudListener
            return fragment
        }
    }
}
