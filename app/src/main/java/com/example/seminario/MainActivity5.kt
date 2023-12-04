package com.example.seminario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seminario.databinding.ActivityMain4Binding
import com.example.seminario.databinding.ActivityMain5Binding

class MainActivity5 : AppCompatActivity() {

    private lateinit var binding: ActivityMain5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain5Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnVolverPrincipal.setOnClickListener {
            val intent = Intent(this@MainActivity5, MainActivity::class.java)
            startActivity(intent)
        }
    }
}