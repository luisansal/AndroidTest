package com.luisansal.jetpack.model.api

import com.luisansal.jetpack.model.api.dto.AuthorResponse
import io.reactivex.Single
import retrofit2.http.GET

interface AuthorApi {

    @GET("bins/m6jx3")
    fun getAuthors():
            Single<AuthorResponse>


}