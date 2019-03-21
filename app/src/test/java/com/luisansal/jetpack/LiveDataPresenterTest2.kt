package com.luisansal.jetpack

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.google.android.gms.maps.model.LatLng
import com.luisansal.jetpack.di.AppComponentTest2
import com.luisansal.jetpack.di.DaggerAppComponentTest2
import com.luisansal.jetpack.model.MyApplication
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.model.domain.Visit
import com.luisansal.jetpack.model.repository.UserRepository
import com.luisansal.jetpack.ui.fragments.ListUserFragment
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentInteractor
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentMVP
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentPresenter
import com.luisansal.jetpack.ui.viewmodel.RoomViewModel
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.util.ArrayList
import javax.inject.Inject


@RunWith(RobolectricTestRunner::class)
class LiveDataPresenterTest2 : AppComponentTest2{
    override fun inject(testToInject: LiveDataInteractorTest2) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun inject(testToInject: LiveDataPresenterTest2) {
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
    lateinit var listUserFragmentView: ListUserFragmentMVP.View

    @Inject
    lateinit var listUserFragmentInteractor: ListUserFragmentInteractor

    @Inject
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var mContext: Context

    lateinit var presenter: ListUserFragmentPresenter

    @Before
    fun setup() {
        mContext = ApplicationProvider.getApplicationContext<Context>()
        val app = mContext.applicationContext as MyApplication
        DaggerAppComponentTest2.builder().application(app)
                .build()
                .inject(this)

        generateData()
        presenter = ListUserFragmentPresenter(listUserFragmentView, listUserFragmentInteractor)
    }

    fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver2(handler = onChangeHandler)
        observe(observer, observer)
    }

    @Test
    @Throws(Exception::class)
    fun validarRvUsuariosPopulado() {

        val roomViewModel = RoomViewModel()
        Mockito.`when`(listUserFragmentView.roomViewModel).thenReturn(roomViewModel)

        val allUsers = LivePagedListBuilder(userRepository.allUsersPaging, 50).build()

        Mockito.`when`(listUserFragmentView.rvUsers).thenReturn(RecyclerView(mContext))

        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        Mockito.`when`(listUserFragmentView.lifecycle).thenReturn(lifecycle)

        presenter.init()
        presenter.populateRoomViewModel(allUsers)
        presenter.populateAdapterRv(allUsers)

        Thread.sleep(1000)

        presenter.validarRvUsuariosPopulado()

        verify(listUserFragmentView).rvUsuariosPopulado()
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
