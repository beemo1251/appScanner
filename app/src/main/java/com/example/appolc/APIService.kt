package com.example.appolc

import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @POST("Persona/user?usuario=SUPER&contraseña=a123456A")
    open fun postUser(): Call<UserResponse>
    //fun getUserLogin(@Url url:String):Response<UserResponse>
}