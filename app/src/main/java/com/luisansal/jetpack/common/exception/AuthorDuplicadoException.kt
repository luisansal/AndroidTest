package com.luisansal.jetpack.common.exception

import com.luisansal.jetpack.model.domain.Author


class AuthorDuplicadoException(val author : Author) : Exception("Author ${author.nombre} con DNI ${author.dni} duplicado ") {
}