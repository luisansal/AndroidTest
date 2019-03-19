package com.luisansal.jetpack

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.ActivityTestRule
import com.luisansal.jetpack.di.DaggerAppComponentTest
import com.luisansal.jetpack.model.MyApplication
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.model.repository.UserRepository
import com.luisansal.jetpack.ui.adapters.PagedUserAdapter
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentInteractor
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentMVP
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Thread.sleep
import javax.inject.Inject


@RunWith(MockitoJUnitRunner::class)
class LiveDataInteractorTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mActivity = ActivityTestRule(MainActivity::class.java)

    @Mock
    lateinit var listUserFragmentPresenter: ListUserFragmentMVP.Presenter

    lateinit var mContext: Context

    @Inject
    lateinit var mInteractor: ListUserFragmentInteractor

    @Inject
    lateinit var userRepository: UserRepository

//    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        val app = mActivity.activity.applicationContext as MyApplication
        DaggerAppComponentTest.builder().application(app)
                .build()
                .inject(this)
        mContext = ApplicationProvider.getApplicationContext<Context>()

//        val db = Room.inMemoryDatabaseBuilder(
//                mContext, MyRoomDatabase::class.java).build()
//        userDao = db.userDao()
    }

//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }

    fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver(handler = onChangeHandler)
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

}
