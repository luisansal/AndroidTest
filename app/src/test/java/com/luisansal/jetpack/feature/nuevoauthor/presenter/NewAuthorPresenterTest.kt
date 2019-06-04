package com.luisansal.jetpack.feature.nuevoauthor.presenter

import com.luisansal.jetpack.dagger.base.BaseIntegrationTest
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.ui.mvp.author.NewAuthorFragmentMVP
import com.luisansal.jetpack.ui.mvp.author.NewAuthorFragmentPresenter
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import javax.inject.Inject

class NewAuthorPresenterTest : BaseIntegrationTest() {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Inject
    lateinit var newAuthorPresenter: NewAuthorFragmentPresenter

    @Mock
    lateinit var mView: NewAuthorFragmentMVP.View

    @Before
    fun setup() {
        daggerComponent.inject(this)
        newAuthorPresenter.setView(mView)
    }

    @Test
    fun `guardar author`() {
        val author = Author("0000001", "Luis", "Salazar")
        Mockito.`when`(mView.author).thenReturn(author)

        newAuthorPresenter.guardarAuthor()

        Mockito.verify(mView).notificarGuardado()
    }

    @Test
    fun `encontrar author`() {
        `guardar author`()

        val dni = "0000001"
        Mockito.`when`(mView.dni).thenReturn(dni)

        newAuthorPresenter.buscarAuthor()

        Mockito.verify(mView).authorEncontrado()
    }

    @Test
    fun `verificar que se limpien los campos`() {

        newAuthorPresenter.limpiarCampos()

        Mockito.verify(mView).camposVacios()
    }

    @Test
    fun `verificar author duplicado`() {
        `guardar author`()

        val author = Author("0000001", "Luis", "Salazar")
        Mockito.`when`(mView.author).thenReturn(author)

        newAuthorPresenter.guardarAuthor()

        Mockito.verify(mView).authorDuplicado(ArgumentMatchers.anyString())

    }

    @Test
    fun `contador corutinas`() {
        val expected = 0
        val result = runBlocking {
            contadorHastaCero(10)
        }
        assertEquals(expected, result)
    }

    suspend fun contadorHastaCero(num : Int) : Int {
        var result = 0
        // Will be launched in the mainThreadSurrogate dispatcher
        for (i in num downTo 0) {
            waitUiThread(1000)
            result = i
        }
        return result
    }

    @Test
    fun `mostrar authors`(){

        newAuthorPresenter.mostrarAuthors()

        verify(mView).mostrarAuthors(ArgumentMatchers.anyList())
    }
}