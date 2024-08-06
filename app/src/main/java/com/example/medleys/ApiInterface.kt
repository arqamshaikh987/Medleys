package com.example.medleys

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("X-RapidAPI-Key: 96477739edmsh87e6f9c794956b1p14e6adjsn8d0a7a6ae9e9",
            "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query:String) : Call<MyData>

}