package com.example.kraftopia.adapter

import com.squareup.moshi.Json

data class AddressItem(
    val name: String,
    val phoneNumber: String,
    val locality: String,
    val city: String,
    val state: String,
    val pinCode: Long
)