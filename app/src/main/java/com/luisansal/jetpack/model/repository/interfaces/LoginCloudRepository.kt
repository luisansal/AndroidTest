package com.luisansal.jetpack.model.repository.interfaces

import com.luisansal.jetpack.model.domain.Login
import io.reactivex.Single

interface LoginCloudRepository {
    fun getLogin(usr: String, pwd: String): Single<Login>
}