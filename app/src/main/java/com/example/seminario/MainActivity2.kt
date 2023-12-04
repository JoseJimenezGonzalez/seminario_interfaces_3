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
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Calendar
import java.util.Date

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

        //Fecha de nacimiento
        binding.tietEdad.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
            val picker = builder.build()

            picker.addOnPositiveButtonClickListener { selectedDateInMillis ->
                val selectedDate = Date(selectedDateInMillis)
                val currentDate = Date()

                if (isMayorDeEdad(selectedDate, currentDate)) {
                    // La persona es mayor de edad, realiza la acción correspondiente
                    binding.tietEdad.setText(picker.headerText)
                    ageIsValid = true
                } else {
                    // La persona no es mayor de edad, puedes mostrar un mensaje o realizar otra acción
                    Toast.makeText(this, "Debes ser mayor de edad", Toast.LENGTH_SHORT).show()
                    ageIsValid = false
                }
            }

            picker.show(supportFragmentManager, "jeje")
        }


        //Vemos si pasamos a la otra actividad
        binding.btnInsertar.setOnClickListener {
            //Vamos a ver si la edad es valido o esta a null/blank
            var edadUser = binding.tietEdad.text.toString()
            if(nameIsValid && (ageIsValid||edadUser.isBlank())){
                //Pasamos a la siguiente actividad
                val intent = Intent(this@MainActivity2, MainActivity5::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Falla algo", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun isMayorDeEdad(fechaNacimiento: Date, fechaActual: Date): Boolean {
        val edadMinima = 18 // La edad mínima para ser considerado mayor de edad

        val calendarNacimiento = Calendar.getInstance().apply { time = fechaNacimiento }
        val calendarActual = Calendar.getInstance().apply { time = fechaActual }

        val aniosDiferencia = calendarActual.get(Calendar.YEAR) - calendarNacimiento.get(Calendar.YEAR)

        // Verificar si la persona tiene al menos la edad mínima
        return if (calendarActual.get(Calendar.DAY_OF_YEAR) < calendarNacimiento.get(Calendar.DAY_OF_YEAR)) {
            aniosDiferencia - 1 >= edadMinima
        } else {
            aniosDiferencia >= edadMinima
        }
    }
}