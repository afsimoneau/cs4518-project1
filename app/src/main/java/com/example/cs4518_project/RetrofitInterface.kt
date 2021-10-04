package com.example.cs4518_project

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("weather?q=worcester&appid=f193d421b69fc36dd7228f65061dfcb2")
    fun getData() : Call<WeatherData>

}