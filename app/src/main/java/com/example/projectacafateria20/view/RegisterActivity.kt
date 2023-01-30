package com.example.projectacafateria20.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.projectacafateria20.R
import com.example.projectacafateria20.viewmodel.CafeteriaViewmodel

class RegisterActivity : AppCompatActivity() {
    lateinit var cafeteriaViewmodel: CafeteriaViewmodel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registre)

        cafeteriaViewmodel = ViewModelProvider(this).get(CafeteriaViewmodel::class.java)

        var buttonRegister = findViewById<Button>(R.id.buttonRegistrarse)
        var buttonCancelar = findViewById<Button>(R.id.buttonCancelar)

        var nom = findViewById<EditText>(R.id.editTextNomUsuari).text.toString()
        var constrasenya = findViewById<EditText>(R.id.editTextTextContrasenyaUsuari).text.toString()
        var correu = findViewById<EditText>(R.id.editTextTextCorreuUsuari).text.toString()

        buttonRegister.setOnClickListener {

            if (nom != "" && constrasenya !=  "" && correu != "") {
                cafeteriaViewmodel.nouClient(this, nom, constrasenya, correu)

                Toast.makeText(this, "S'ha creat el usuari.", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Sisplau emplena tots els camps.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}