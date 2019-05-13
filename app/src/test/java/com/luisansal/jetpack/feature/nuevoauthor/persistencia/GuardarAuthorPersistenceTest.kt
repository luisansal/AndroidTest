package com.luisansal.jetpack.feature.nuevoauthor.persistencia

import com.luisansal.jetpack.dagger.base.BaseIntegrationTest
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.model.repository.AuthorRepository
import org.junit.Before
import org.junit.Test
import javax.inject.Inject


class GuardarAuthorPersistenceTest : BaseIntegrationTest() {

    @Inject
    lateinit var authorRepository: AuthorRepository

    @Before
    fun setup() {
        daggerComponent.inject(this)
    }

    @Test
    fun `guardar author`() {
        val author = Author("234234","luis","sanchez")

        val completableTest = authorRepository.saveAuthor(author).test()
        completableTest.awaitTerminalEvent()

        val singleTest = authorRepository.getAuthorByDni("234234").test()
        singleTest.awaitTerminalEvent()

        singleTest.assertNoErrors().assertValue {
            (it.dni.equals(author.dni))
        }
    }

    @Test
    fun encontrarAuthor() {
        `guardar author`()

        val singleTest = authorRepository.getAuthorByDni("234234").test()
        singleTest.awaitTerminalEvent()

        singleTest.assertNoErrors().assertValue {
            (it.id != null)
        }
    }

}