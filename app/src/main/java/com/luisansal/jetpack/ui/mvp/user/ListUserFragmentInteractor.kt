package com.luisansal.jetpack.ui.mvp.user

import androidx.paging.LivePagedListBuilder
import com.luisansal.jetpack.model.repository.UserRepository
import javax.inject.Inject

class ListUserFragmentInteractor @Inject constructor(
                                                     private val userRepository: UserRepository) : ListUserFragmentMVP.Interactor {

    private lateinit var mPresenter : ListUserFragmentMVP.Presenter

    override fun attachPresenter(presenter : ListUserFragmentMVP.Presenter){
        mPresenter = presenter
    }

    override fun validarRvUsuariosPopulado() {
        if (mPresenter.adapterUsuarios.itemCount > 0) {
            mPresenter.rvUsuariosPopulado()
        } else {
            mPresenter.rvUsuariosNoPopulado()
        }
    }

    override fun validarCantidadPaginacion(numeroComparar: Int) {
        if (numeroComparar == mPresenter.numUsers) {
            mPresenter.cantidadValida()
        } else {
            mPresenter.cantidadInvalida()
        }
    }

    override fun setupLivePaged() {
        //        PagedList.Config config = new PagedList.Config.Builder()
        //                .setPageSize(100)
        //                .setInitialLoadSizeHint(100)
        //                .setPrefetchDistance(100)
        //                .setEnablePlaceholders(true)
        //                .build();
        val users = LivePagedListBuilder(userRepository.allUsersPaging, 50).build()

        mPresenter.populateRoomViewModel(users)
        mPresenter.populateAdapterRv(users)

    }

}