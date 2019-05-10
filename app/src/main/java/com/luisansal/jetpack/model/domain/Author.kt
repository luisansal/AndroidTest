package com.luisansal.jetpack.model.domain


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "tblauthor", indices = [Index(value = ["dni"], unique = true)])
data class Author (var dni: String?,var name: String?,var lastName: String?) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

}
