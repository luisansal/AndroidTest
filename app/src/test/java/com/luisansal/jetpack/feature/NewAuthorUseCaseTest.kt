package com.luisansal.jetpack.feature

import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.dagger.base.BaseIntegrationTest
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.model.usecase.AuthorUseCaseImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class NewAuthorUseCaseTest : BaseIntegrationTest() {

    @Inject
    lateinit var authorUseCaseImpl: AuthorUseCaseImpl

    @Before
    fun setup() {
        daggerComponent.inject(this)
    }

    @Test
    fun guardarAuthor() {
        val syncObject = Object()


        val author = Author()
        author.dni = "234234"
        author.name = "luis"
        author.lastName = "sanchez"

        var ok = false
        authorUseCaseImpl.saveAuthor(author, object : BaseCompletableObserver() {
            override fun onComplete() {
                ok = true

            }

            override fun onError(e: Throwable) {

            }
        })

        Assert.assertTrue(ok)
    }

    @Test
    fun encontrarAuthor() {
        guardarAuthor()

        var ok = false
        authorUseCaseImpl.getAuthorByDni("234234", object : BaseSingleObserver<Author>() {
            override fun onSuccess(t: Author) {
                ok = true
            }

            override fun onError(e: Throwable) {

            }
        })
        Assert.assertTrue(ok)
    }

}