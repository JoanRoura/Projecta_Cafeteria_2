package com.example.projectacafateria20.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectacafateria20.model.Plat

class CafeteriaSharedViewModel: ViewModel() {
    companion object {
        val listPlats: ArrayList<String> = ArrayList()
        val listPreus: ArrayList<Int> = ArrayList()

        fun sumarPreus(): Int {
            return listPreus.sum()
        }

    }


}