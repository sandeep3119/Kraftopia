package com.example.kraftopia.data.api

import com.example.kraftopia.data.model.AddressResponse
import com.example.kraftopia.data.model.AuthResponse
import com.example.kraftopia.data.model.RegisterRequest
import com.example.kraftopia.data.model.RegisterResponse

interface ApiHelper {
    suspend fun getUserToken(email:String,password:String):AuthResponse
    suspend fun getUserAddress(): List<AddressResponse>
    suspend fun registerUser(registerRequest: RegisterRequest):RegisterResponse
}