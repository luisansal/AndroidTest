package com.luisansal.jetpack.feature

import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.dagger.base.BaseIntegrationTest
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.model.usecase.AuthorUseCaseImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class GuardarAuthorPersistenceTest : BaseIntegrationTest()  {

    @Inject
    lateinit var authorUseCase : AuthorUseCaseImpl

    @Before
    fun setup(){
        daggerComponent.inject(this)
    }

    inner class SaveAuthorSubscriber : BaseCompletableObserver() {
        override fun onComplete() {
            Assert.assertTrue(true)
        }

        override fun onError(e: Throwable) {
            Assert.assertTrue(false)
        }
    }

    @Test
    fun guardarAuthor(){
        val author = Author()
        author.dni = "234234"
        author.name = "luis"
        author.lastName = "sanchez"

        authorUseCase.saveAuthor(author,SaveAuthorSubscriber())
    }

}