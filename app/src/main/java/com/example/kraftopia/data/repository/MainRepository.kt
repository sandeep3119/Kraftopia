package com.example.kraftopia.data.repository

import com.example.kraftopia.data.api.ApiHelper
import com.example.kraftopia.data.model.AddressResponse
import com.example.kraftopia.data.model.RegisterRequest
import com.example.kraftopia.data.model.RegisterResponse
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getUserToken(email:String,password:String) =  apiHelper.getUserToken(email, password).auth_token

    suspend fun getUserAddress():List<AddressResponse>{
       return apiHelper.getUserAddress()
    }
    suspend fun registerUser(registerRequest: RegisterRequest):RegisterResponse{
        return apiHelper.registerUser(registerRequest)
    }

}