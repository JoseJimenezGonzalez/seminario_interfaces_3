package com.example.seminario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seminario.databinding.ActivityMain2Binding
import com.example.seminario.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}