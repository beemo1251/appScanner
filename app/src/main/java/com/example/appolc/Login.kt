package com.example.appolc

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        if (binding.txtUsuario.toString() != "" && binding.txtPassword.toString() != "") {
            val intent: Intent = Intent(this, MainActivity:: class.java)
            getApiService()
            //startActivity(intent)
        }
        //Toast.makeText(this, binding.txtUsuario.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun getApiService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder().serializeNulls().create()
            ))
            .build()
        val apiService: APIService = retrofit.create(APIService::class.java)
        val call: Call<UserResponse> = apiService.postUser()

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    Log.d("Resp: ", response.body().toString())
                } else {
                    Log.d("Error", "Something happened");
                    return;
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                Log.d("Error", t.toString());
            }

        })
    }
}