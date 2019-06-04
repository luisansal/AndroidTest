package com.luisansal.jetpack.model.api

import com.luisansal.jetpack.model.api.dto.LoginResponse
import io.reactivex.Single
import retrofit2.http.GET

interface LoginApi {

    @GET("bins/iuwcv")
    fun login():
            Single<LoginResponse>
}