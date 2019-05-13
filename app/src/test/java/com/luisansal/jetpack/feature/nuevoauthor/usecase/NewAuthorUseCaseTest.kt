package com.luisansal.jetpack.feature.nuevoauthor.usecase

import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.dagger.base.BaseIntegrationTest
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.model.usecase.interfaces.AuthorUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class NewAuthorUseCaseTest : BaseIntegrationTest() {

    @Inject
    lateinit var authorUseCase: AuthorUseCase

    @Before
    fun setup() {
        daggerComponent.inject(this)
    }

    @Test
    fun `guardar author`() {
        val author = Author("234234","luis","sanchez")

        var ok = false

        authorUseCase.guardarAuthor(author, object : BaseCompletableObserver() {
            override fun onComplete() {
                ok = true

            }

            override fun onError(e: Throwable) {

            }
        })

        Assert.assertTrue(ok)
    }

    @Test
    fun `encontrar author`() {
        `guardar author`()

        var ok = false
        authorUseCase.getAuthorByDni("234234", object : BaseSingleObserver<Author>() {
            override fun onSuccess(t: Author) {
                ok = true
            }

            override fun onError(e: Throwable) {

            }
        })
        Assert.assertTrue(ok)
    }
}