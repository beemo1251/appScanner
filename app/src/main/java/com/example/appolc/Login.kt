package com.example.appolc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.appolc.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private  lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener { login() }
    }

    private fun login() {
        if (binding.txtUsuario.toString() != "" && binding.txtPassword.toString() != "") {
            val intent: Intent = Intent(this, MainActivity:: class.java)
            startActivity(intent)
        }
        //Toast.makeText(this, binding.txtUsuario.toString(), Toast.LENGTH_SHORT).show()
    }
}