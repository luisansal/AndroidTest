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
        val author = Author("0000001","Pedro","Alvarez")
        val completableTest = authorUseCase.guardarAuthor(author,object: BaseCompletableObserver(){}).test()
        completableTest.awaitTerminalEvent()

        val completableTest2 = authorUseCase.guardarAuthor(author,object: BaseCompletableObserver(){}).test()
        completableTest.awaitTerminalEvent()

        completableTest.assertNoErrors().assertComplete()
    }

    @Test
    fun `encontrar author`() {
        `guardar author`()

        val dni = "0000001"
        val singleTest = authorUseCase.buscarAuthorByDni(dni,object : BaseSingleObserver<Author>(){}).test()
        singleTest.awaitTerminalEvent()

        singleTest.assertNoErrors().assertValue {
            it.dni.equals(dni)
        }
    }

    @Test
    fun `comprobar campos obligatorios`(){
        val author = Author("1234567","Pepito","Jimenez")
        Assert.assertTrue(authorUseCase.comprobarCamposObligatorios(author))
    }

    @Test
    fun `comprobar dni`(){
        val dni = "0000002"
        Assert.assertTrue(authorUseCase.validarDniUsuario(dni))
    }

    @Test
    fun `comprobar author duplicado`(){
        `guardar author`()
        val dni = "0000001"
        Assert.assertTrue(authorUseCase.validarAuthorDuplicado(dni))
    }
}