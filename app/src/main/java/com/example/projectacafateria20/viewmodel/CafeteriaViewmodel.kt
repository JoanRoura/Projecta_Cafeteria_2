package com.example.projectacafateria20.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.projectacafateria20.model.Plat
import com.example.projectacafateria20.model.Usuari
import com.example.projectacafateria20.repository.Repositori

class CafeteriaViewmodel : ViewModel() {

    var usuari: LiveData<List<Usuari>>? = null;

    var plat: LiveData<List<Plat>>? = null;


    fun nouClient(context: Context, nom: String, contrasenya: String, correu: String){
        var usuari = Usuari(nom, contrasenya, correu)

        Repositori.insertUsuari(context, usuari);
    }

    fun obtenirPlats(context: Context, categoria: Int):  LiveData<List<Plat>>? {

        plat = Repositori.getPlats(context, categoria)
        return plat
    }

    fun loginClient(context: Context, nom: String, contrasenya: String): LiveData<List<Usuari>>?{
        usuari = Repositori.loginClient(context, nom, contrasenya)
        Log.i("CafeteriaViewmodel","$usuari")
        return usuari
    }





}