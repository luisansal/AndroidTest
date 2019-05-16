package com.luisansal.jetpack.feature.nuevoauthor.presenter

import com.luisansal.jetpack.dagger.base.BaseIntegrationTest
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.ui.mvp.author.NewAuthorFragmentMVP
import com.luisansal.jetpack.ui.mvp.author.NewAuthorFragmentPresenter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import javax.inject.Inject

class NewAuthorPresenterTest : BaseIntegrationTest() {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Inject
    lateinit var newAuthorPresenter : NewAuthorFragmentPresenter

    @Mock
    lateinit var mView : NewAuthorFragmentMVP.View

    @Before
    fun setup(){
        daggerComponent.inject(this)
    }

    @Test
    fun `guardar author`(){
        val author = Author("0000001","Luis","Salazar")
        Mockito.`when`(mView.author).thenReturn(author)

        newAuthorPresenter.setView(mView)
        newAuthorPresenter.guardarAuthor()

        Mockito.verify(mView).notificarGuardado()
    }

    @Test
    fun `encontrar author`(){

    }

    @Test
    fun `verificar que se limpien los campos`(){

    }
}