package com.luisansal.jetpack.ui.fragments.mvp

import androidx.paging.LivePagedListBuilder
import com.luisansal.jetpack.model.repository.UserRepository

class ListUserFragmentInteractor(val presenter: ListUserFragmentMVP.Presenter) : ListUserFragmentMVP.Interactor {

    override fun validarRvUsuariosPopulado() {
        if (presenter.adapterUsuarios.itemCount > 0) {
            presenter.rvUsuariosPopulado()
        } else {
            presenter.rvUsuariosNoPopulado()
        }
    }

    override fun validarCantidadPaginacion(numeroComparar: Int) {
        if (numeroComparar == presenter.numUsers) {
            presenter.cantidadValida()
        } else {
            presenter.cantidadInvalida()
        }
    }

    private var mNumUsers: Int = 0

    override fun setupLivePaged() {
        //        PagedList.Config config = new PagedList.Config.Builder()
        //                .setPageSize(100)
        //                .setInitialLoadSizeHint(100)
        //                .setPrefetchDistance(100)
        //                .setEnablePlaceholders(true)
        //                .build();
        val users = LivePagedListBuilder(UserRepository.newInstance(presenter.context).allUsersPaging, 50).build()

        presenter.populateRoomViewModel(users)
        presenter.populateAdapterRv(users)

    }

}