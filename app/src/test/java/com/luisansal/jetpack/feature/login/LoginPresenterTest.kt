package com.luisansal.jetpack.feature.login

import com.luisansal.jetpack.dagger.base.BaseIntegrationTest
import com.luisansal.jetpack.model.domain.Login
import com.luisansal.jetpack.ui.mvp.login.LoginActivityMVP
import com.luisansal.jetpack.ui.mvp.login.LoginActivityPresenter
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import javax.inject.Inject

class LoginPresenterTest : BaseIntegrationTest()  {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var mView : LoginActivityMVP.View

    @Inject
    lateinit var mPresenter : LoginActivityPresenter

    @Before
    fun setup(){
        daggerComponent.inject(this)
        mPresenter.setView(mView)
    }

    @Test(timeout=2000)
    fun `login`(){

        val login = Login("luisansal","12345678")

        whenever(mView.login).thenReturn(login)

        mPresenter.validarLogin()

        verify(mView).mensajeLoginCorrecto()

    }
}