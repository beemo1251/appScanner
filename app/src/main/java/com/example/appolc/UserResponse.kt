package com.example.appolc

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("usuario") var usuario: String,
    @SerializedName("password") var password: String,
    @SerializedName("nombre") var nombre: String
)