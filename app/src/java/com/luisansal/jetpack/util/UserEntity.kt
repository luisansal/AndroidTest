package com.luisansal.jetpack.util

import com.google.gson.annotations.SerializedName


class UserEntity {

    @SerializedName("access_token")
    var accessToken: String? = null

    @SerializedName("refresh_token")
    var refreshToken: String? = null

    @SerializedName("codUsuario")
    var codUsuario: String? = null

    @SerializedName("codZona")
    var codZona: String? = null

    @SerializedName("nombre")
    var nombre: String? = null

    @SerializedName("codRegion")
    var codRegion: String? = null

    @SerializedName("telefonoFijo")
    var telefonoFijo: String? = null

    @SerializedName("telefonoMovil")
    var telefonoMovil: String? = null

    @SerializedName("codPais")
    var codPais: String? = null

    @SerializedName("nombrePais")
    var nombrePais: String? = null

    @SerializedName("nombreJefe")
    var nombreJefe: String? = null

    @SerializedName("codRol")
    var codRol: String? = null

    @SerializedName("rol")
    var rol: String? = null

    @SerializedName("password")
    var password: String? = null

    @SerializedName("apellidoPaterno")
    var apellidoPaterno: String? = null

    @SerializedName("apellidoMaterno")
    var apellidoMaterno: String? = null

    @SerializedName("cub")
    var cub: String? = null

    @SerializedName("codMensaje")
    var codMsg: String? = null

    @SerializedName("mensaje")
    var mensaje: String? = null

    @SerializedName("logID")
    var logID: String? = null

    @SerializedName("esLider")
    var esLider: String? = null

    @SerializedName("nivel")
    var nivel: String? = null

    @SerializedName("seccion")
    var seccion: String? = null

    @SerializedName("fechaFinAdmin")
    var fechaFinAdmin: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("documentoIdentidad")
    var documentoIdentidad: String? = null

    @SerializedName("consultoraID")
    var consultoraId: String? = null

    @SerializedName("codigoConsultora")
    var codigoConsultora: String? = null

    @SerializedName("latitud")
    var latitud: String? = null

    @SerializedName("longitud")
    var longitud: String? = null

    @SerializedName("codTerritorio")
    var codTerritorio: String? = null

    @SerializedName("seccionGestionLider")
    var seccionGestionLider: String? = null

    @SerializedName("georeferenciacion")
    var georeferenciacion: String? = null

}