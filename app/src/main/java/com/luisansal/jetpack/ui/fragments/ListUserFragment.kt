package com.luisansal.jetpack.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.luisansal.jetpack.R
import com.luisansal.jetpack.interfaces.CrudListener
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentMVP
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentPresenter
import com.luisansal.jetpack.ui.viewmodel.RoomViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class ListUserFragment : Fragment(), ListUserFragmentMVP.View {
    override var presenterForTest: ListUserFragmentPresenter
        get() = mPresenter
        set(value) {
            mPresenter = value
        }

//    @Inject
    lateinit var mPresenter : ListUserFragmentPresenter

    override fun validarRvUsuariosPopulado() {
        mPresenter.validarRvUsuariosPopulado()
    }

    override fun rvUsuariosPopulado() {

    }

    override fun rvUsuariosNoPopulado() {

    }

    override val rvUsers: RecyclerView
        get() {
            return mRvUsers
        }

    override val roomViewModel: RoomViewModel
        get() {
            return mRoomViewModel
        }

    private lateinit var mRvUsers: RecyclerView
    private lateinit var mRoomViewModel: RoomViewModel

    private lateinit var btnNuevoUsuario: Button

    override fun setupRv() {
        mRvUsers.setHasFixedSize(true)
        mRvUsers.layoutManager = LinearLayoutManager(context)
    }


    override fun setupRoomViewModel() {
        mRoomViewModel = ViewModelProviders.of(this).get(RoomViewModel::class.java)
    }

    override fun initView(view: View) {
        mRvUsers = view.findViewById(R.id.rvUsers)
        btnNuevoUsuario = view.findViewById(R.id.btnNuevoUsuario)
    }

    override fun onClickBtnNuevoUsuario() {
        btnNuevoUsuario!!.setOnClickListener { view -> mCrudListener!!.onNew() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_user, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        mPresenter = ListUserFragmentPresenter(this)
        mPresenter.init()
    }

    companion object {

        var TAG = ListUserFragment::class.java.getName()
        private var mCrudListener: CrudListener<*>? = null

        // TODO: Rename and change types and number of parameters
        fun newInstance(crudListener: CrudListener<*>): ListUserFragment {
            val fragment = ListUserFragment()
            val args = Bundle()
            fragment.arguments = args
            mCrudListener = crudListener
            return fragment
        }
    }
}
