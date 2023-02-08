package com.example.projectacafateria20.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projectacafateria20.model.Plat
import com.example.projectacafateria20.model.Usuari

@Dao
interface CafeteriaDAO {
    // Usuaris
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUsuari(client: Usuari)

    @Query("SELECT * FROM usuari")
    fun getUsuari(): LiveData<List<Usuari>>

    @Update
    fun updateUsuari(usuari: Usuari)

    @Delete
    fun deleteUsuari(usuari: Usuari)

    @Query("SELECT * FROM usuari WHERE name =:nombre AND password =:contrasenya")
    fun getUsuariLogin(nombre:String, contrasenya: String): LiveData<List<Usuari>>

    // Plats
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPlat(plat: Plat)

    @Query("SELECT * FROM plat WHERE category =:categoria")
    fun getPlats(categoria: Int): LiveData<List<Plat>>

    @Update
    fun updatePlat(plat: Plat)

    @Delete
    fun deletePlat(plat: Plat)


}