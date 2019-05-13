package com.luisansal.jetpack.feature.nuevoauthor.validation

import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.model.validation.AuthorValidation
import org.junit.Assert
import org.junit.Test


class ValidarAuthorTest{

    @Test
    fun `longitud dni correcto`(){
        val dni = "1234567"
        Assert.assertTrue(AuthorValidation.dniCorrecto(dni))
    }

    @Test
    fun `verificar campos obligatorios`(){
        val author = Author("jj","jn","Lopez")
        Assert.assertTrue(AuthorValidation.comprobarCamposObligatorios(author))
    }
}