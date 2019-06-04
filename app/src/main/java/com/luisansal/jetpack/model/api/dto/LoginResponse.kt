package com.luisansal.jetpack.model.api.dto

import com.google.gson.annotations.SerializedName
import com.luisansal.jetpack.model.domain.Login

class LoginResponse : BaseResponse() {

    @SerializedName("resultado")
    lateinit var resultado: Login

}