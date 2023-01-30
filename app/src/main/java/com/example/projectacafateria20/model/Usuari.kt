package com.example.projectacafateria20.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "usuari")
data class Usuari (
    @ColumnInfo(name = "name")
    var nom : String,
    @ColumnInfo(name = "password")
    var contrasenya : String,
    @ColumnInfo(name = "email")
    var correu : String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}