package com.luisansal.jetpack.feature

import com.luisansal.jetpack.dagger.base.BaseIntegrationTest
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.model.usecase.interfaces.AuthorUseCase
import com.luisansal.jetpack.ui.mvp.NewAuthorFragmentMVP
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
    fun guardarAuthor(){

        val author = Author("0001","jorge","espinoza")
        `when`(view.author).thenReturn(author)

        presenter.guardarAuthor()
        verify(view).notificarGuardado()
    }

    @Test
    fun `verificar campos obligatorios`(){
        val author = Author("0001","jorge","espinoza")
        `when`(view.author).thenReturn(author)

        presenter.verificarCamposObligatorios()

        verify(view).notificarGuardado()
    }


}