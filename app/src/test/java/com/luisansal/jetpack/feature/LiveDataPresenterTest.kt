package com.luisansal.jetpack.feature

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import com.luisansal.jetpack.model.MyApplication
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.model.repository.UserRepository
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentMVP
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentPresenter
import com.luisansal.jetpack.ui.viewmodel.RoomViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLooper
import java.util.ArrayList


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [27], application = MyApplication::class)
class LiveDataPresenterTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var listUserFragmentView: ListUserFragmentMVP.View

    @Mock
    lateinit var listUserFragmentInteractor: ListUserFragmentMVP.Interactor

    lateinit var mContext: Context

    lateinit var presenter: ListUserFragmentPresenter

    @Before
    fun setup() {
        //Si vamos a probar el presentador, deberemos inicializarlo y no hacerle mock, solo hacer mock a la vista e interactor.
        presenter = ListUserFragmentPresenter(listUserFragmentView, listUserFragmentInteractor)

        //Instanciamos el contexto, sin Robolectric esto no podría ser posible
        mContext = ApplicationProvider.getApplicationContext<Context>()

        //Guarda los usuarios en la BD
        loadData()
    }

    @Test
    @Throws(Exception::class)
    fun validarRvUsuariosPopulado() {
        //Obtenemos todos los usuarios con paginación
        val allUsers = LivePagedListBuilder(UserRepository.newInstance(mContext).allUsersPaging, 50).build()

        //Con el metodo `when` podemos inyectarle valores de retorno
        Mockito.`when`(listUserFragmentView.rvUsers).thenReturn(RecyclerView(mContext))

        //Ya que la vista
        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        Mockito.`when`(listUserFragmentView.lifecycle).thenReturn(lifecycle)

        //Hacemos mock también del RoomViewModel
        Mockito.`when`(listUserFragmentView.roomViewModel).thenReturn(RoomViewModel())

        //invocar a los metodos que interactuan con el presenter y el RV que queremos validar
        presenter.setupRv()
        presenter.setupRoomViewModel()

        presenter.populateRoomViewModel(allUsers)
        presenter.populateAdapterRv(allUsers)

        //Darle un pequeño sleep de 1 segundo
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks()

        //invocar el metodo para validar dicho RV populado
        presenter.validarRvUsuariosPopulado()

        //Verificar la interacción con la vista, si el metodo es invocado, quiere decir que todo esta correcto debido a que el presentador llama a dicho metodo cuando valida correctamente que el RV es populado, el metodo `verify` de Mockito nos ayudara a validar dicho proceso.
        verify(listUserFragmentView).rvUsuariosPopulado()
    }

    //Guarda la data a la bd simulada por Robolectric
    fun loadData(){
        val userRepo = UserRepository.newInstance(mContext)
        if(userRepo.allUsersInline.isNotEmpty())
            return

        userRepo.deleteAll()

        var user = User()
        user.name = "Juan"
        user.lastName = "Alvarez"
        user.dni = "05159410"
        val lastUserId = userRepo.save(user)


        val users = ArrayList<User>()
        for (i in 0..999) {
            user = User()
            user.name = "User ${i+1}"
            user.lastName = "Apell ${i+1}"
            user.dni = "dni ${i+1}"
            users.add(user)
        }
        userRepo.saveAll(users)
        userRepo.closeConn()
    }

    @Test
    fun verificarParametros (){
        Assert.assertEquals("something",System.getProperty("arg1"))
    }
}