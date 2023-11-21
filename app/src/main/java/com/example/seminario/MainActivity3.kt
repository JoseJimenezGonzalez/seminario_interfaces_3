package com.example.seminario

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seminario.databinding.ActivityMain2Binding
import com.example.seminario.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        actionButton()
    }
    //Revisar porque no es correcto
    private fun actionButton() {
        binding.btnEnviar.setOnClickListener {
            val email = binding.tietCorreo.text.toString()
            val nombreUsuario = binding.tietNombre.text.toString()
            val password = binding.tietPassword.text.toString()
            if(nombreUsuario.isEmpty()){
                binding.tilNombre.error = "El nombre no puede ser vacio"
            }else{
                binding.tilNombre.error = null // Limpiar el error si hay uno
                binding.tilNombre.boxStrokeColor = Color.GREEN
            }
            if(!(email.contains("@") && email.contains("."))){
                binding.tilCorreo.error = "El correo tiene que contener al menos una @ y un ."
            }else{
                binding.tilCorreo.error = null // Limpiar el error si hay uno
                binding.tilCorreo.boxStrokeColor = Color.GREEN
            }
            if(password.length < 6){
                binding.tilPassword.error = "La constraseña al menos debe de tener 6 caracteres"
            }else{
                binding.tilPassword.error = null // Limpiar el error si hay uno
                binding.tilPassword.boxStrokeColor = Color.GREEN
            }
        }

    }
}