package com.luisansal.jetpack.model.repository

import com.luisansal.jetpack.model.api.LoginApi
import com.luisansal.jetpack.model.domain.Login
import com.luisansal.jetpack.model.repository.interfaces.LoginCloudRepository
import io.reactivex.Single
import javax.inject.Inject

class LoginCloudRepositoryImpl @Inject constructor(private val loginApi: LoginApi) : LoginCloudRepository {

    override fun getLogin(usr: String, pwd: String): Single<Login> {
        return loginApi.login()
                .filter {
                    it.resultado.usr == usr && it.resultado.pwd == pwd
                }
                .toSingle()
                .map {
                    it.resultado
                }
    }
}