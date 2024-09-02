package com.example.fmo.fd


import com.google.gson.annotations.SerializedName

data class FoodData(
    @SerializedName("body")
    val body: Body,
    @SerializedName("header")
    val header: Header
)