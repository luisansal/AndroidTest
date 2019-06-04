package com.luisansal.jetpack.model.api.dto

import com.google.gson.annotations.SerializedName
import com.luisansal.jetpack.model.domain.Author

class AuthorResponse : BaseResponse() {

    @SerializedName("resultado")
    lateinit var resultado: List<Author>

}