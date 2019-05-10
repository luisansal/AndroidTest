package com.luisansal.jetpack.ui.mvp

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.ui.adapters.PagedUserAdapter
import javax.inject.Inject

class ListUserFragmentPresenter @Inject constructor(private val mView: ListUserFragmentMVP.View,
                                                    private val mInteractor: ListUserFragmentMVP.Interactor) : ListUserFragmentMVP.Presenter {
    override val numUsers: Int
        get() = mNumUsers

    override val adapterUsuarios: PagedUserAdapter
        get() {
            return mAdapter
        }
    lateinit var mAdapter: PagedUserAdapter
    private var mNumUsers: Int = 0

    override fun validarRvUsuariosPopulado() {
        mInteractor.validarRvUsuariosPopulado()
    }

    override fun rvUsuariosPopulado() {
        mView.rvUsuariosPopulado()
    }

    override fun rvUsuariosNoPopulado() {
        mView.rvUsuariosNoPopulado()
    }

    override fun populateAdapterRv(users: LiveData<PagedList<User>>) {
        mAdapter = PagedUserAdapter()
//        mView.roomViewModel.getUsers().observe(this, adqapter::submitList);
        mView.roomViewModel.users.observe(mView, Observer<PagedList<User>> { mAdapter.submitList(it) })

        mView.rvUsers.adapter = mAdapter
        mNumUsers = mAdapter.itemCount
    }

    override fun cantidadValida() {

    }

    override fun cantidadInvalida() {

    }

    override fun validarCantidadPaginacion(numeroComparar: Int) {
        mInteractor.validarCantidadPaginacion(numeroComparar)
    }



    override fun init() {
        mInteractor.attachPresenter(this)
        setupRoomViewModel()
        setupRv()
        mInteractor.setupLivePaged()
        mView.onClickBtnNuevoUsuario()
    }

    override fun setupRoomViewModel() {
        mView.setupRoomViewModel()
    }

    override fun setupRv() {
        mView.setupRv()
    }

    override fun populateRoomViewModel(users: LiveData<PagedList<User>>) {
        mView.roomViewModel.users = users
    }
}