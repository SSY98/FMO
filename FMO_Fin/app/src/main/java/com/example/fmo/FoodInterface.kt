package com.example.fmo

import com.example.fmo.fd.FoodData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


val  pageno = 1
val  numofrows = 1
val  data_type = "json"

interface FoodInterface {
    @GET("getFoodNtrItdntList?ServiceKey=r8AvgufZYRJbGBf1MArePyhnJc8SmPrSV9NWwXwbtTBdm7ZHLBxjsV%2B4LfZGBwS2r%2BICVWkxmRZe5cemrqWNxA%3D%3D")
    fun GetFood(
        @Query("desc_kor") desc_korr:String,
        @Query("pageNo") pageno:Int,
        @Query("numOfRows") numofrows:Int,
        @Query("type") data_type:String
    ): Call<FoodData>
}