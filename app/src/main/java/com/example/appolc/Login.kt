package com.example.appolc

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.appolc.databinding.ActivityLoginBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Login : AppCompatActivity() {

    private  lateinit var binding: ActivityLoginBinding
    private val BASE_URL = "http://20.230.232.224:1152/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener { login() }
    }

    private fun login() {
        val usu = binding.txtUsuario.text.toString()
        val pass = binding.txtPassword.text.toString()
        if (usu != "" && pass != "") {
            getApiService(usu, pass)
        } else {
            Toast.makeText(this@Login, "Ingrese un usuario y contraseña", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getApiService(usu: String, pass: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder().serializeNulls().create()
            ))
            .build()
        val apiService: APIService = retrofit.create(APIService::class.java)
        val call: Call<UserResponse> = apiService.postUser(usu, pass)

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val intent: Intent = Intent(this@Login, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login, "Error de autenticación", Toast.LENGTH_SHORT).show()
                    return;
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                Log.d("Error", t.toString());
            }

        })
    }
}