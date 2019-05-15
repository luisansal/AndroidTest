package com.luisansal.jetpack.model.validation

import com.luisansal.jetpack.model.domain.Author

class AuthorValidation {
    companion object{
        fun dniCorrecto(dni : String) : Boolean{
            return false
        }

        fun comprobarCamposObligatorios(author: Author) : Boolean{
            return false
        }
    }

}