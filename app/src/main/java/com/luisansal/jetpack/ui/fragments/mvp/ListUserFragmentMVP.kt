package com.luisansal.jetpack.ui.fragments.mvp

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.ui.adapters.PagedUserAdapter
import com.luisansal.jetpack.ui.viewmodel.RoomViewModel

interface ListUserFragmentMVP {

    interface View : LifecycleOwner {
        var presenterForTest : ListUserFragmentPresenter
        fun initView(view: android.view.View)
        fun setupRoomViewModel()
        fun setupRv()
        fun onClickBtnNuevoUsuario()
        val rvUsers : RecyclerView
        val roomViewModel : RoomViewModel
        fun validarRvUsuariosPopulado()
        fun rvUsuariosPopulado()
        fun rvUsuariosNoPopulado()
    }

    interface Presenter{
        fun init()
        fun setupRoomViewModel()
        fun setupRv()
        fun populateAdapterRv(users: LiveData<PagedList<User>>)
        fun populateRoomViewModel(users: LiveData<PagedList<User>>)
        val context : Context
        val adapterUsuarios : PagedUserAdapter
        fun validarCantidadPaginacion(numeroComparar : Int)
        fun cantidadValida()
        fun cantidadInvalida()
        val numUsers : Int
        fun validarRvUsuariosPopulado()
        fun rvUsuariosPopulado()
        fun rvUsuariosNoPopulado()
    }

    interface Interactor{
        fun attachPresenter(mPresenter: ListUserFragmentMVP.Presenter)
        fun setupLivePaged()
        fun validarCantidadPaginacion(numeroComparar : Int)
        fun validarRvUsuariosPopulado()
    }
}