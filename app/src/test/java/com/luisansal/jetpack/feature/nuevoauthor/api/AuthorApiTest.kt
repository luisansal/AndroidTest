package com.luisansal.jetpack.feature.nuevoauthor.api

import com.luisansal.jetpack.dagger.base.BaseIntegrationTest
import com.luisansal.jetpack.model.api.AuthorApi
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class AuthorApiTest : BaseIntegrationTest() {

    @Inject
    lateinit var authorApi: AuthorApi

    @Before
    fun setup(){
        daggerComponent.inject(this)
    }

    @Test
    fun `obtener authors`(){

        val authorApiTest = authorApi.getAuthors().test()
        authorApiTest.awaitTerminalEvent()

        authorApiTest.assertNoErrors().assertValue {
            it.httpStatus == 200
        }
    }
}