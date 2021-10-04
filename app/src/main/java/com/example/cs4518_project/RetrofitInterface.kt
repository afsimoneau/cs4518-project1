package com.example.cs4518_project

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("forecast/?id=524901&appid=f193d421b69fc36dd7228f65061dfcb2")
  //  @GET("posts")

   // fun getData() : Call<List<Weather>>
    fun getData() : Call<WeatherData>


  //  fun listRepos(@Path("user") user: String?): Call<String?>?


}