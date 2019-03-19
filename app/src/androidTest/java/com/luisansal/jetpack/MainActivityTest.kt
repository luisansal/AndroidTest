package com.luisansal.jetpack

import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentInteractor
import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentMVP
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.luisansal.jetpack.model.database.MyRoomDatabase
import com.luisansal.jetpack.ui.adapters.PagedUserAdapter
import org.junit.Rule
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.luisansal.jetpack.model.dao.UserDao
import com.luisansal.jetpack.model.repository.UserRepository
import org.junit.After


@RunWith(MockitoJUnitRunner::class)
class MainActivityTest
//: AndroidJUnitRunner()
{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mContext: Context

    @Mock
    lateinit var listUserFragmentPresenter: ListUserFragmentMVP.Presenter
//
//    lateinit var mInteractor: ListUserFragmentInteractor

    private lateinit var mDatabase: MyRoomDatabase

    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        mContext = InstrumentationRegistry.getContext()
//        mInteractor = ListUserFragmentInteractor(listUserFragmentPresenter)
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

    fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver(handler = onChangeHandler)
        observe(observer, observer)
    }

    @Test

    fun obtenerListadoUsuarios() {

//        Mockito.`when`(listUserFragmentPresenter.context).thenReturn(mContext)

        val adapter = PagedUserAdapter()

        val users = LivePagedListBuilder(userDao.findAllUsersPaging(), 50).build()


//        adapter.submitList()

//        users.observeOnce {
//            adapter.submitList(it)
//        }


//        Mockito.`when`(listUserFragmentPresenter.adapterUsuarios).thenReturn(adapter)
//
//        mInteractor.setupLivePaged()
//        mInteractor.validarRvUsuariosPopulado()
//
//        Mockito.verify(listUserFragmentPresenter).rvUsuariosNoPopulado()
    }

//    fun <T> mockPagedList(list: List<T>): PagedList<T> {
//        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
//        Mockito.`when`(pagedList.get(ArgumentMatchers.anyInt())).then { invocation ->
//            val index = invocation.arguments.first() as Int
//            list[index]
//        }
//        Mockito.`when`(pagedList.size).thenReturn(list.size)
//        return pagedList
//    }

//    @Test
//    fun cantidadAdecuadaDePaginacion() {
//        Mockito.`when`(listUserFragmentPresenter.context).thenReturn(mContext)
//
//        mInteractor.setupLivePaged()
//        mInteractor.validarCantidadPaginacion(50)
//
//        Mockito.verify(listUserFragmentPresenter).cantidadValida()
//    }
}
