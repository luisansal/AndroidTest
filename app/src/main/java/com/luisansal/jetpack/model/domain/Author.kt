package com.luisansal.jetpack.model.domain


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tblauthor", indices = [Index(value = ["dni"], unique = true)])
data class Author (@SerializedName("dni") var dni: String, @SerializedName("nombre") var nombre: String, @SerializedName("apellido") var apellido: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

}
