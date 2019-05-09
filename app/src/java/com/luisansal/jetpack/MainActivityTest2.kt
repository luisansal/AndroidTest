package com.luisansal.jetpack

import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentInteractor
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentMVP
import org.junit.runner.RunWith
import org.mockito.Mock
import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.luisansal.jetpack.model.database.MyRoomDatabase
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import androidx.room.Room
import com.luisansal.jetpack.model.dao.UserDao
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.model.repository.UserRepository
import com.luisansal.jetpack.ui.adapters.PagedUserAdapter
import org.junit.*
import org.mockito.junit.MockitoJUnitRunner

@Ignore
@RunWith(MockitoJUnitRunner::class)
class MainActivityTest2
//: AndroidJUnitRunner()
{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mContext: Context

    @Mock
    lateinit var listUserFragmentPresenter: ListUserFragmentMVP.Presenter

    lateinit var interactor: ListUserFragmentInteractor

    private lateinit var mDatabase: MyRoomDatabase

    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        interactor = ListUserFragmentInteractor(listUserFragmentPresenter)
        mDatabase = Room.inMemoryDatabaseBuilder(mContext,
                MyRoomDatabase::class.java!!)
                .allowMainThreadQueries()
                .build()

        userDao = mDatabase.userDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        mDatabase.close()
    }

    @Test
    fun obtenerListadoUsuarios() {

//        Mockito.`when`(listUserFragmentPresenter.context).thenReturn(mContext)
//
//        val adapter = PagedUserAdapter()
//
//        val allUsers = LivePagedListBuilder(UserRepository.newInstance(mContext).allUsersPaging, 50).build()
//
//        allUsers.observeOnce {
//            adapter.submitList(it)
//        }
//        Mockito.`when`(listUserFragmentPresenter.adapterUsuarios).thenReturn(adapter)
//
//        interactor.setupLivePaged()
//        interactor.validarRvUsuariosPopulado()
//
//        Mockito.verify(listUserFragmentPresenter).rvUsuariosNoPopulado()
    }

    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
        Mockito.`when`(pagedList.get(ArgumentMatchers.anyInt())).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        Mockito.`when`(pagedList.size).thenReturn(list.size)
        return pagedList
    }

    @Test
    fun cantidadAdecuadaDePaginacion() {
        Mockito.`when`(listUserFragmentPresenter.context).thenReturn(mContext)

        interactor.setupLivePaged()
        interactor.validarCantidadPaginacion(50)

        Mockito.verify(listUserFragmentPresenter).cantidadValida()
    }

    fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver(handler = onChangeHandler)
        observe(observer, observer)
    }
}
