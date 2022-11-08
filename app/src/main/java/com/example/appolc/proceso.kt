package com.example.appolc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.appolc.databinding.ActivityProcesoBinding

class proceso : AppCompatActivity() {

    private lateinit var binding: ActivityProcesoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProcesoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            registrarEnvio()
        }

        //val objIntent: Intent = intent
        //var listLines: Array<Array<String>> = objIntent.getSerializableExtra("Lines") as Array<Array<String>>
    }

    fun registrarEnvio() {

    }
}