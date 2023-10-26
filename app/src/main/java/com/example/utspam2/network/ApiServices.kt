package com.example.utspam2.network

import com.example.utspam2.model.ResponseUser
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

    @GET("api/users")
    fun getListUsers(@Query("per_page") perPage: String): Call<ResponseUser>

    @GET("api/users/{id}")
    fun getUser(@Path("id") id: String): Call<ResponseUser>


}