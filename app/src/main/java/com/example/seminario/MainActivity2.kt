package com.example.seminario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.seminario.databinding.ActivityMain2Binding
import com.example.seminario.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    var nameIsValid = false
    var ageIsValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        Crea una app con un formulario con dos campos:
        nombre y edad • El nombre es requerido y deberá
        tener como mínimo dos caracteres
        La edad no es obligatoria, pero si se introduce se
        debe ser mayor de edad
        */
        //Nombre
        binding.tietNombre.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //No es necesario en este caso
            }

            override fun onTextChanged(nombre: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val nombreUsuario = nombre.toString()
                //Evaluamos que tenga que ser valido
                if(nombreUsuario.length >= 2 && nombreUsuario.isNotBlank()){
                    binding.tilNombre.error = null
                    nameIsValid = true
                }else{
                    binding.tilNombre.error = "El nombre debe de tener mas de dos caracteres"
                    nameIsValid = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                //No es necesario en este caso
            }

        })

        // Edad
        binding.tietEdad.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // No es necesario en este caso
            }

            override fun onTextChanged(edad: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (edad.isNullOrEmpty()){
                    binding.tilEdad.error = null
                    ageIsValid = true
                } else if(edad.toString().toInt() >= 18){
                    binding.tilEdad.error = null
                    ageIsValid = true
                }else{
                    binding.tilEdad.error = "La edad tiene que ser mas de 18 si se introduce"
                    ageIsValid = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                // No es necesario en este caso
            }
        })


        //Vemos si pasamos a la otra actividad
        binding.btnInsertar.setOnClickListener {
            if(nameIsValid && ageIsValid){
                //Pasamos a la siguiente actividad
                val intent = Intent(this@MainActivity2, MainActivity5::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Falla algo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}