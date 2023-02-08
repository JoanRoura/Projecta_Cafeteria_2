package com.example.projectacafateria20.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.projectacafateria20.database.CafeteriaDatabase
import com.example.projectacafateria20.model.Plat
import com.example.projectacafateria20.model.Usuari
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repositori {

    companion object{
        var cafeteriaDatabase : CafeteriaDatabase? = null;

        var usuari: LiveData<List<Usuari>>? = null;

        var plat: LiveData<List<Plat>>? = null;

        // Inicialitza la base de dades
        fun initializeDB(context: Context): CafeteriaDatabase {
            return CafeteriaDatabase.getDatabase(context)
        }

        // CRUD Usuaris
        fun insertUsuari(context: Context, usuari: Usuari){
            cafeteriaDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                cafeteriaDatabase!!.CafeteriaDAO().addUsuari(usuari)
            }
        }

        fun getUsuari(context: Context): LiveData<List<Usuari>>? {
            cafeteriaDatabase = initializeDB(context)

            usuari = cafeteriaDatabase!!.CafeteriaDAO().getUsuari()
            return usuari;
        }

        fun loginClient(context: Context, nom: String, contrasenya: String): LiveData<List<Usuari>>? {
            cafeteriaDatabase = initializeDB(context)

            usuari = cafeteriaDatabase!!.CafeteriaDAO().getUsuariLogin(nom, contrasenya)
            return usuari
        }

        // CRUD Plats

        fun insertPlat(context: Context, plat: Plat){
            cafeteriaDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                cafeteriaDatabase!!.CafeteriaDAO().addPlat(plat)
            }
        }

        fun getPlats(context: Context, categoria: Int): LiveData<List<Plat>>? {
            cafeteriaDatabase = initializeDB(context)

            plat = cafeteriaDatabase!!.CafeteriaDAO().getPlats(categoria)
            return plat;
        }


    }
}