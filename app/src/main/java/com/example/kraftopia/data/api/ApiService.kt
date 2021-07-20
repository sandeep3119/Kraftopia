package com.example.kraftopia.data.api

import com.example.kraftopia.data.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService   {
    @POST("token/login/")
    suspend fun getUserToken(@Body body:AuthRequest):AuthResponse

    @POST("users/")
    suspend fun registerUser(@Body body:RegisterRequest): RegisterResponse

    @GET("address/")
    //suspend fun getUserAddress(@Header("Authorization") headerRequest:HeaderRequest):AddressResponse
    suspend fun getUserAddress(@Header("Authorization") headerToken:String): List<AddressResponse>
}