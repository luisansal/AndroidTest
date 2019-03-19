package com.luisansal.jetpack

import android.content.Context
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.luisansal.jetpack.di.DaggerAppComponent
import com.luisansal.jetpack.di.DaggerAppComponentTest
import com.luisansal.jetpack.model.MyApplication
import com.luisansal.jetpack.model.domain.User
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
import java.lang.Thread.sleep
import javax.inject.Inject


@RunWith(MockitoJUnitRunner::class)
class LiveDataFragmentTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mActivity = ActivityTestRule(MainActivity::class.java)

    lateinit var mScenario: FragmentScenario<ListUserFragment>

    @Before
    fun setup() {
        val app = mActivity.activity.applicationContext as MyApplication

        // The "state" and "factory" arguments are optional.
        val fragmentArgs = Bundle().apply {
            //Ingresar los inputs necesarios
            //putInt("numElements", 0)
        }

        //Usar esto para pruebas no gráficas
//        mScenario = launchFragment<ListUserFragment>(fragmentArgs)

        //Usar esto para pruebas graficas en los fragments
        val mScenario = launchFragmentInContainer<ListUserFragment>(
                fragmentArgs)

    }

    fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver(handler = onChangeHandler)
        observe(observer, observer)
    }

    @Test
    @Throws(Exception::class)
    fun validarSoloFragment() {
//          Se pueden llamar a metodos dentro del fragment y probar acciones
//        mScenario.onFragment {
//
//        }

        onView(withId(R.id.btnNuevoUsuario)).check(matches(withText("Nuevo Usuario")))

    }
}
