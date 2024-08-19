package com.marceloacuna.appmovil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //se crean las variables
        val usernametext: EditText = findViewById(R.id.editTextTextEmailAddress)
        val passwordtext: EditText = findViewById(R.id.editTextTextPassword)
        val loginButton: Button = findViewById(R.id.buttonLogin)
        val textregister: TextView = findViewById(R.id.textRegistrarse)


        //accion del boton



        loginButton.setOnClickListener{
            val nombreusuario = usernametext.text.toString()
            val passwordusuario = passwordtext.text.toString()

            if(nombreusuario != ""  && nombreusuario != null)
            {
                if(passwordusuario != "" && passwordusuario != null)
                {
                   Toast.makeText(this, "Nombre de Usuario: $nombreusuario\nContraseña : $passwordusuario ", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(this, "Ingrese su Contraseña ", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this, "Ingrese su Email ", Toast.LENGTH_SHORT).show()
            }
        }


        //redirigir a la ventana Registrar
        textregister.setOnClickListener{
            val intent = Intent(this,RegistrarActivity::class.java)
            startActivity(intent)
        }
    }
}