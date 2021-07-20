package com.example.kraftopia.data.api

import android.content.SharedPreferences
import com.example.kraftopia.data.model.*

import javax.inject.Inject

class ApiHelperImpl @Inject constructor(val pref:SharedPreferences, val apiService: ApiService):ApiHelper {

    override suspend fun getUserToken( email:String,password:String ):AuthResponse {
        val authRequest=AuthRequest(email, password)
        val editor: SharedPreferences.Editor =pref.edit()
        val token=apiService.getUserToken(authRequest)
        editor.putString("session_token","Token ${token.auth_token}")
        editor.apply()
        return token
    }

    override suspend fun getUserAddress(): List<AddressResponse> {
        return apiService.getUserAddress(pref.getString("session_token",null)!!)
    }

    override suspend fun registerUser(registerRequest:RegisterRequest): RegisterResponse {
        return apiService.registerUser(registerRequest)
    }

}