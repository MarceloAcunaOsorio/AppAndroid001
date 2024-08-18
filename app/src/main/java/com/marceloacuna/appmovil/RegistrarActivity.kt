package com.marceloacuna.appmovil

import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.DatePickerDialog
import android.content.Intent
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import java.util.Calendar

class RegistrarActivity : AppCompatActivity() {


    private lateinit var etUsername:EditText
    private lateinit var etEmail:EditText
    private lateinit var etPassword:EditText
    private lateinit var etPassword2:EditText
    private lateinit var etBirthDate:EditText
    private lateinit var btnRegister:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }






        val textLogi: TextView = findViewById(R.id.textLogin)
        etUsername = findViewById(R.id.txtnombreUsuario)
        etEmail = findViewById(R.id.TxtEmail)
        etPassword = findViewById(R.id.TxtPassword)
        etPassword2 = findViewById(R.id.TxtPassword2)
        etBirthDate = findViewById(R.id.TextBirthDate)
        btnRegister = findViewById(R.id.buttonRegistrar)



        //Obtener Fecha actual
        etBirthDate.setOnClickListener{

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

        //Crear el DatePickerDialog
            val datePickerDialog = DatePickerDialog(this,
                { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                    //Actualizar el texto del campo de texto con la fecha seleccionada
                    etBirthDate.setText("$selectedDay/${selectedMonth+1}/$selectedYear")
            },year,month,day)

        // Mostrar el DatePickerDialog
            datePickerDialog.show()
        }



        //ir a la pestaña de login
        textLogi.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }



        //Registrar usuario
        btnRegister.setOnClickListener{
            val username = etUsername.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val password2 = etPassword2.text.toString()
            val birtDate = etBirthDate.text.toString()


            //añadir logica de registro
            if(password == password2)
            {
                //crear nuevo usuario
                val newUser = User(username,email,password,password2,birtDate)

                val sharePreferences = getSharedPreferences(username)

            }
        }
        
    }
}


