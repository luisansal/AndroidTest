package com.luisansal.jetpack

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.ActivityTestRule
import com.luisansal.jetpack.di.DaggerAppComponentTest
import com.luisansal.jetpack.model.MyApplication
import com.luisansal.jetpack.model.repository.UserRepository
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentInteractor
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentMVP
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentPresenter
import com.luisansal.jetpack.ui.viewmodel.RoomViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject


@RunWith(MockitoJUnitRunner::class)
class LiveDataPresenterTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mActivity = ActivityTestRule(MainActivity::class.java)

    @Mock
    lateinit var listUserFragmentView: ListUserFragmentMVP.View

    @Inject
    lateinit var listUserFragmentInteractor: ListUserFragmentInteractor

    @Inject
    lateinit var userRepository: UserRepository

    lateinit var mContext: Context

    lateinit var presenter: ListUserFragmentPresenter

    @Before
    fun setup() {
        val app = mActivity.activity.applicationContext as MyApplication

        DaggerAppComponentTest.builder().application(app)
                .build()
                .inject(this)

        mContext = ApplicationProvider.getApplicationContext<Context>()

        presenter = ListUserFragmentPresenter(listUserFragmentView, listUserFragmentInteractor)
    }

    fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver(handler = onChangeHandler)
        observe(observer, observer)
    }

    @Test
    @Throws(Exception::class)
    fun validarRvUsuariosPopulado() {

        val roomViewModel = ViewModelProviders.of(mActivity.activity).get(RoomViewModel::class.java)
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
}
