package com.luisansal.jetpack.other

import com.luisansal.jetpack.ui.fragments.mvp.ListUserFragmentMVP
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.luisansal.jetpack.model.database.MyRoomDatabase
import org.junit.Rule
import androidx.room.Room
import com.luisansal.jetpack.model.dao.UserDao
import org.junit.After
import org.mockito.junit.MockitoJUnitRunner


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


    private lateinit var mDatabase: MyRoomDatabase

    private lateinit var userDao: UserDao

    @Before
    fun setup() {

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


    }


    @Test
    fun cantidadAdecuadaDePaginacion() {

    }
}
