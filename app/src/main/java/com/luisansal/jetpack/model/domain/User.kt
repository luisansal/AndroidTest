package com.luisansal.jetpack.model.domain


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbluser")
data class User constructor(var dni: String, var nombre: String?, var apellido: String?) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}
