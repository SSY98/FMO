package com.example.fmo.fd


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("ANIMAL_PLANT")
    val aNIMALPLANT: String,
    @SerializedName("BGN_YEAR")
    val bGNYEAR: String,
    @SerializedName("DESC_KOR")
    val dESCKOR: String,
    @SerializedName("NUTR_CONT1")
    val nUTRCONT1: String,
    @SerializedName("NUTR_CONT2")
    val nUTRCONT2: String,
    @SerializedName("NUTR_CONT3")
    val nUTRCONT3: String,
    @SerializedName("NUTR_CONT4")
    val nUTRCONT4: String,
    @SerializedName("NUTR_CONT5")
    val nUTRCONT5: String,
    @SerializedName("NUTR_CONT6")
    val nUTRCONT6: String,
    @SerializedName("NUTR_CONT7")
    val nUTRCONT7: String,
    @SerializedName("NUTR_CONT8")
    val nUTRCONT8: String,
    @SerializedName("NUTR_CONT9")
    val nUTRCONT9: String,
    @SerializedName("SERVING_WT")
    val sERVINGWT: String
)