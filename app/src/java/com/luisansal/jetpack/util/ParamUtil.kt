package com.luisansal.jetpack.util

import com.google.gson.Gson
import java.io.InputStreamReader

data class Params (var sesion: UserEntity,var rfc: List<RFCEntity>)

class UtilParam {

    inline fun <reified T> getFromFile(filename: String): T {
        val url = javaClass.classLoader?.getResource("$filename.json")
        val reader = InputStreamReader(requireNotNull(url).openStream())
        return Gson().fromJson(reader, T::class.java)
    }

}