package com.luisansal.jetpack

import android.content.Context
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.luisansal.jetpack.di.DaggerAppComponent
import com.luisansal.jetpack.di.DaggerAppComponentTest
import com.luisansal.jetpack.model.MyApplication
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

    lateinit var mContext: Context

    lateinit var presenter: ListUserFragmentPresenter

    lateinit var mScenario: FragmentScenario<ListUserFragment>

    @Before
    fun setup() {
        val app = mActivity.activity.applicationContext as MyApplication

        DaggerAppComponentTest.builder().application(app)
                .build()
                .inject(this)

        mContext = ApplicationProvider.getApplicationContext<Context>()

//        // The "state" and "factory" arguments are optional.
//        val fragmentArgs = Bundle().apply {
//            putInt("numElements", 0)
//        }
//
//        mScenario = launchFragment<ListUserFragment>(fragmentArgs)

//        var listUserFragment : ListUserFragment? = null
//        mScenario.onFragment {
//            listUserFragment = it
//        }
    }

    fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver(handler = onChangeHandler)
        observe(observer, observer)
    }

    @Test
    @Throws(Exception::class)
    fun validarRvUsuariosPopulado() {

        presenter = ListUserFragmentPresenter(listUserFragmentView, listUserFragmentInteractor)


        val allUsers = LivePagedListBuilder(UserRepository.newInstance(mContext).allUsersPaging, 50).build()

        Mockito.`when`(listUserFragmentView.rvUsers).thenReturn(RecyclerView(mContext))

        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        Mockito.`when`(listUserFragmentView.lifecycle).thenReturn(lifecycle)

        val roomViewModel = ViewModelProviders.of(mActivity.activity).get(RoomViewModel::class.java)
        Mockito.`when`(listUserFragmentView.roomViewModel).thenReturn(roomViewModel)

        presenter.init()

        presenter.populateRoomViewModel(allUsers)
        presenter.populateAdapterRv(allUsers)

        Thread.sleep(1000)

        presenter.validarRvUsuariosPopulado()

        verify(listUserFragmentView).rvUsuariosPopulado()
    }
}
