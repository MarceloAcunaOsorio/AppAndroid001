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
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
            val newUser = User(username,email,password,birtDate)
            val sharePreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            val editor = sharePreferences.edit()
            val userJson = sharePreferences.getString("users",null)
            val gson = Gson()

            val userType = object : TypeToken<MutableList<User>>(){}.type


            if(password == password2)

            {
            val users: MutableList<User> = if(userJson != null){
                gson.fromJson(userJson,userType)
            }else
            {
                mutableListOf()
            }
            users.add(newUser)

            val newUserJson = gson.toJson(users)
            editor.putString("users",newUserJson)
            editor.apply()

            Toast.makeText(this,"Registro Exitoso", Toast.LENGTH_LONG).show()

                //ver registro de usuario
                Log.d("UserList",users.joinToString ( "\n" ){user ->
                    "UserName: ${user.username},Email ${user.email} ,Fecha Cumpleaños : ${user.birtDate}"
                })
            }
            else
            {
                Toast.makeText(this, "Claves no son iguales", Toast.LENGTH_SHORT).show()
            }



        }
        
    }
}


