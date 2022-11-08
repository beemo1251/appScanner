package com.example.appolc

import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @POST("Persona/user")
    open fun postUser(@Query("usuario") usu: String, @Query("contraseña") pass: String): Call<UserResponse>
    //fun getUserLogin(@Url url:String):Response<UserResponse>
}