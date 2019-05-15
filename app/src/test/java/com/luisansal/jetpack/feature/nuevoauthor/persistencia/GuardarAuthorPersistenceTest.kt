package com.luisansal.jetpack.feature.nuevoauthor.persistencia

import com.luisansal.jetpack.dagger.base.BaseIntegrationTest
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.model.repository.interfaces.AuthorRepository
import org.junit.After
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

    @After
    fun after() {
        authorRepository.db?.close()
    }

    @Test
    fun `guardar author`() {
        val author = Author("0000001","Luis","Sanchez")
        val completableTest = authorRepository.guardarAuthor(author).test()
        completableTest.awaitTerminalEvent()

        completableTest.assertNoErrors().assertComplete()
    }

    @Test
    fun `encontrar author`() {
        `guardar author`()

        val dni = "0000001"
        val singleTest = authorRepository.buscarAuthorByDni(dni).test()
        singleTest.awaitTerminalEvent()

        singleTest.assertNoErrors().assertValue {
            it.dni.equals(dni)
        }
    }

}