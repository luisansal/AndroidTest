package com.luisansal.jetpack

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.ActivityTestRule
import com.luisansal.jetpack.di.*
import com.luisansal.jetpack.model.MyApplication
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.model.repository.UserRepository
import com.luisansal.jetpack.ui.adapters.PagedUserAdapter
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentInteractor
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentMVP
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.robolectric.RobolectricTestRunner
import java.lang.Thread.sleep
import java.util.ArrayList
import javax.inject.Inject
import javax.inject.Singleton




@RunWith(RobolectricTestRunner::class)
class LiveDataInteractorTest2 : AppComponentTest2 {
    override fun inject(testToInject: LiveDataPresenterTest2) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun inject(testToInject: LiveDataInteractorTest2) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun inject(myApplication: MyApplication) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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

        val app = mContext.applicationContext as MyApplication

        DaggerAppComponentTest2.builder().application(app)
                .build()
                .inject(this)

        generateData()

    }


    fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver2(handler = onChangeHandler)
        observe(observer, observer)
    }

    @Test
    fun validarRvUsuariosPopulado() {
        mInteractor.attachPresenter(listUserFragmentPresenter)

        val user = User()
        user.name = "Luis"
        user.dni = "70668281"

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
    fun validarCantidadUsuarios() {

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

    fun generateData(){

        var user = User()
        user.name = "Juan"
        user.lastName = "Alvarez"
        user.dni = "05159410"

        val users = ArrayList<User>()
        for (i in 0..999) {
            user = User()
            user.name = "User" + (i + 1)
            user.lastName = "Apell" + (i + 1)
            user.dni = "dni" + (i + 1)
            users.add(user)
        }
        userRepository.saveAll(users)

        Log.i("DB_ACTIONS", "Database Populated")
    }
}
