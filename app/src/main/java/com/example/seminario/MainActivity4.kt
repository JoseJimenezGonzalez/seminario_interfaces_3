package com.example.seminario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seminario.databinding.ActivityMain2Binding
import com.example.seminario.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {

    private lateinit var binding: ActivityMain4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}