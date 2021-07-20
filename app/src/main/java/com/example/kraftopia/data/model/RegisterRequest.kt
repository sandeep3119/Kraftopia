package com.example.kraftopia.data.model

import java.util.*


data class RegisterRequest(
    val email:String,
    val password:String,
    val name:String,
    val phone_number:String,
    val dob: String,
    val firebaseId:String,
    )

data class RegisterResponse(
    val email:String,
    val id:Int
)