package com.example.kraftopia.data.model

import com.squareup.moshi.Json


data class AddressResponse(
    val id: Long,
    val name: String,
    @Json(name = "phone_number")
    val phoneNumber: String,
    val locality: String,
    val city: String,
    val state: String,
    @Json(name = "pin_code")
    val pinCode: Long
)