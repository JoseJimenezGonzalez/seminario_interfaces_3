package com.example.seminario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.seminario.databinding.ActivityMain4Binding
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Calendar
import java.util.Date
import java.util.regex.Pattern

class MainActivity4 : AppCompatActivity() {

    private lateinit var binding: ActivityMain4Binding

    var passIsValid = false
    var emailIsValid = false
    var postalCodeIsValid = false
    var ageIsValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBotones()
    }

    // Función para verificar si la persona es mayor de edad
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

    private fun setupBotones() {

        //Fecha de nacimiento
        binding.tietFechaNacimiento.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
            val picker = builder.build()

            picker.addOnPositiveButtonClickListener { selectedDateInMillis ->
                val selectedDate = Date(selectedDateInMillis)
                val currentDate = Date()

                if (isMayorDeEdad(selectedDate, currentDate)) {
                    // La persona es mayor de edad, realiza la acción correspondiente
                    binding.tietFechaNacimiento.setText(picker.headerText)
                    ageIsValid = true
                } else {
                    // La persona no es mayor de edad, puedes mostrar un mensaje o realizar otra acción
                    Toast.makeText(this, "Debes ser mayor de edad", Toast.LENGTH_SHORT).show()
                    ageIsValid = false
                }
            }

            picker.show(supportFragmentManager, "jeje")
        }

        //Contraseña
        binding.tietPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No es necesario en este caso
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Este método se llama cuando el texto cambia
                val password = s.toString()
                // Aqui evaluamos la contraseña
                val pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}\$")
                val matcher = pattern.matcher(password)

                if (!matcher.matches()) {
                    binding.tilPassword.error =
                        "La contraseña debe tener al menos 8 caracteres, un número, una letra mayúscula y una minúscula."
                    passIsValid = false
                } else {
                    binding.tilPassword.error = null // Borra cualquier mensaje de error
                    passIsValid = true
                }
            }
            override fun afterTextChanged(s: Editable?) {
                // No es necesario en este caso
            }
        })
        //Correo
        binding.tietCorreo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No es necesario en este caso
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Este método se llama cuando el texto cambia
                val password = s.toString()
                // Aqui evaluamos el correo
                if (!password.contains("@") || !password.contains(".")) {
                    binding.tilCorreo.error =
                        "El correo debe contener una @ y un ."
                    emailIsValid = false
                } else {
                    binding.tilCorreo.error = null // Borra cualquier mensaje de error
                    emailIsValid = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // No es necesario en este caso
            }
        })
        //Codigo postal
        binding.tietCodigoPostal.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // No es necesario en este caso
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Cada vez que cambia el texto se llama
                val codigoPostal = s.toString()
                //Evaluamos que tenga que ser valido
                if(codigoPostal.length != 5){
                    binding.tilCodigoPostal.error =
                        "El codigo postal debe de tener exactamente 5 caracteres."
                    postalCodeIsValid = false
                }else{
                    binding.tilCodigoPostal.error = null // Borra cualquier mensaje de error
                    postalCodeIsValid = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                // No es necesario en este caso
            }

        })

        //Enviar
        binding.btnEnviar.setOnClickListener {
            //Vamos a ver si el codigo postal es valido o esta a null/blank
            var resultadoCodigoPostal = ""
            resultadoCodigoPostal = binding.tietCodigoPostal.text.toString()
            if(!ageIsValid){
                Toast.makeText(this, "La edad es obligatoria", Toast.LENGTH_SHORT).show()
            }
            if(!emailIsValid){
                Toast.makeText(this, "El email es obligatorio", Toast.LENGTH_SHORT).show()
            }
            if(!passIsValid){
                Toast.makeText(this, "La contraseña es obligatoria", Toast.LENGTH_SHORT).show()
            }
            if(resultadoCodigoPostal.isNotBlank() && !postalCodeIsValid){
                Toast.makeText(this, "El codigo postal no es obligatorio, pero si se pone debe de ser correcto", Toast.LENGTH_SHORT).show()
            }
            if(ageIsValid && emailIsValid && passIsValid && (resultadoCodigoPostal.isBlank() || postalCodeIsValid)){
                //Pasamos a la siguiente actividad
                val intent = Intent(this@MainActivity4, MainActivity5::class.java)
                startActivity(intent)
            }
        }
    }
}