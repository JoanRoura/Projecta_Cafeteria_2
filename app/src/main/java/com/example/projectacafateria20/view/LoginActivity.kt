package com.example.projectacafateria20.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projectacafateria20.R
import com.example.projectacafateria20.viewmodel.CafeteriaViewmodel

class LoginActivity : AppCompatActivity() {
    lateinit var cafeteriaViewmodel: CafeteriaViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        cafeteriaViewmodel = ViewModelProvider(this).get(CafeteriaViewmodel::class.java)

        var buttonLogin = findViewById<Button>(R.id.buttonLogin)
        var buttonRegister = findViewById<Button>(R.id.buttonRegister)


        buttonLogin.setOnClickListener {
            var nomUsuari = findViewById<EditText>(R.id.editTextNomUsuariL).text.toString()
            var contrasenyaUsuari = findViewById<EditText>(R.id.editTextContrasenyaUsuariL).text.toString()

            cafeteriaViewmodel.loginClient(this, nomUsuari, contrasenyaUsuari)!!.observe(this, Observer { llistaUsuaris ->
                Toast.makeText(this,"Bienvenido: ${llistaUsuaris[0].nom}" , Toast.LENGTH_SHORT).show()
                val intentMainActivity = Intent(this, MainActivity::class.java)
                startActivity(intentMainActivity)
            })
        }

        buttonRegister.setOnClickListener {
            val intentRegisterActivity = Intent(this, RegisterActivity::class.java)
            startActivity(intentRegisterActivity)
        }
    }
}