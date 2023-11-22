package com.example.seminario

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.seminario.databinding.ActivityMain2Binding
import com.example.seminario.databinding.ActivityMain4Binding
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.regex.Pattern

class MainActivity4 : AppCompatActivity() {

    private lateinit var binding: ActivityMain4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBotones()
    }

    private fun setupBotones() {
        binding.tietFechaNacimiento.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
            val picker = builder.build()

            picker.addOnPositiveButtonClickListener {
                binding.tietFechaNacimiento.setText(picker.headerText)
            }

            picker.show(supportFragmentManager, "jeje")
        }



        binding.btnEnviar.setOnClickListener {

        }

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
                } else {
                    binding.tilPassword.error = null // Borra cualquier mensaje de error
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // No es necesario en este caso
            }
        })

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
                }else{
                    binding.tilCodigoPostal.error = null // Borra cualquier mensaje de error
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                // No es necesario en este caso
            }

        })
    }
}