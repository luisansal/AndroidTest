package com.luisansal.jetpack.feature.nuevoauthor.api

import com.luisansal.jetpack.dagger.base.BaseIntegrationTest
import com.luisansal.jetpack.model.api.AuthorApi
import com.luisansal.jetpack.model.api.LoginApi
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class LoginApiTest : BaseIntegrationTest() {

    @Inject
    lateinit var loginApi: LoginApi

    @Before
    fun setup(){
        daggerComponent.inject(this)
    }

    @Test
    fun `login`(){

        val usr = "luisansal"
        val pwd = "12345678"

        val loginApiTest = loginApi.login().test()
        loginApiTest.awaitTerminalEvent()

        loginApiTest.assertNoErrors().assertValue {
            it.resultado.usr.equals(usr) && it.resultado.pwd.equals(pwd)
        }
    }
}