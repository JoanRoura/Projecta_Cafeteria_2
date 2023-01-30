package com.example.projectacafateria20.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "plat")
data class Plat (
    @ColumnInfo(name = "name")
    var nom : String,
    @ColumnInfo(name = "price")
    var preu : String,
    @ColumnInfo(name = "image")
    var imatge : String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}