package com.luisansal.jetpack.feature.nuevoauthor.presenter

import com.luisansal.jetpack.dagger.base.BaseIntegrationTest
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.ui.mvp.author.NewAuthorFragmentMVP
import com.luisansal.jetpack.ui.mvp.author.NewAuthorFragmentPresenter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import javax.inject.Inject

class NewAuthorPresenterTest : BaseIntegrationTest() {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Inject
    lateinit var presenter : NewAuthorFragmentPresenter

    @Mock
    lateinit var view : NewAuthorFragmentMVP.View

    @Before
    fun setup(){
        daggerComponent.inject(this)
    }

    @Test
    fun `guardar author`(){

        val author = Author("0001","jorge","espinoza")
        `when`(view.author).thenReturn(author)

        presenter.setView(view)
        presenter.guardarAuthor()
        verify(view).notificarGuardado()
    }

    @Test
    fun `encontrar author`(){
        `guardar author`()

        `when`(view.dni).thenReturn("0001")

        presenter.setView(view)
        presenter.buscarAuthor()

        verify(view).authorEncontrado()
    }

    @Test
    fun `verificar que se limpien los campos`(){

        val author = Author("","","")
        `when`(view.author).thenReturn(author)

        presenter.setView(view)
        presenter.limpiarCampos()

        verify(view).camposVacios()
    }
}