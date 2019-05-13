package com.luisansal.jetpack.feature.listadopaginadouser

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.test.core.app.ApplicationProvider
import com.luisansal.jetpack.dagger.base.BaseIntegrationTest
import com.luisansal.jetpack.other.OneTimeObserver
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.model.repository.UserRepository
import com.luisansal.jetpack.ui.adapters.PagedUserAdapter
import com.luisansal.jetpack.ui.mvp.ListUserFragmentInteractor
import com.luisansal.jetpack.ui.mvp.ListUserFragmentMVP
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import java.lang.Thread.sleep
import java.util.ArrayList
import javax.inject.Inject

class LiveDataInteractorTest : BaseIntegrationTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var listUserFragmentPresenter: ListUserFragmentMVP.Presenter

    lateinit var mContext: Context

    @Inject
    lateinit var mInteractor: ListUserFragmentInteractor

    @Inject
    lateinit var userRepository: UserRepository


    @Before
    fun setup() {
        mContext = ApplicationProvider.getApplicationContext<Context>()
        daggerComponent.inject(this)

        generateData()
    }


    fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver(handler = onChangeHandler)
        observe(observer, observer)
    }

    @Test
    fun `validar recyclerview de usuarios populados`() {
        mInteractor.attachPresenter(listUserFragmentPresenter)

        val user = User("70668281","Luis","Sanchez")

        userRepository.save(user)
        val allUsers = LivePagedListBuilder(userRepository.allUsersPaging, 50).build()

        val adapter = PagedUserAdapter()

        allUsers.observeOnce {
            adapter.submitList(it)
            sleep(30)
        }

        Mockito.`when`(listUserFragmentPresenter.adapterUsuarios).thenReturn(adapter)

        mInteractor.validarRvUsuariosPopulado()
        verify(listUserFragmentPresenter).rvUsuariosPopulado()
    }

    @Test
    fun `validar la cantidad de usuarios`() {

        mInteractor.attachPresenter(listUserFragmentPresenter)

        val allUsers = LivePagedListBuilder(userRepository.allUsersPaging, 50).build()

        val adapter = PagedUserAdapter()

        allUsers.observeOnce {
            adapter.submitList(it)
            sleep(30)
        }

        sleep(2000)

        Mockito.`when`(listUserFragmentPresenter.numUsers).thenReturn(adapter.itemCount)

        mInteractor.validarCantidadPaginacion(1001)

        verify(listUserFragmentPresenter).cantidadValida()
    }

    fun generateData() {

        val users = ArrayList<User>()
        for (i in 0..999) {
            val user = User("dni" + (i + 1), "User" + (i + 1), "Apell" + (i + 1))
            users.add(user)
        }
        userRepository.saveAll(users)

        Log.i("DB_ACTIONS", "Database Populated")
    }
}
