package com.idemia.wearherapp.data.source.network.api

import com.idemia.wearherapp.data.models.WeatherResponse
import com.idemia.wearherapp.data.source.network.response.NetworkResponse
import com.idemia.wearherapp.data.source.network.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    suspend fun getWeatherbyCity(@Query("q") location :String , @Query("key")  apikey:String , @Query("format") format :String ,@Query("num_of_days") num_of_days :String) : NetworkResponse<WeatherResponse, Response.ErrorResponse>

    @GET(".")
    suspend fun getWeatherbyLatLong(@Query("q") location :String , @Query("key")  apikey:String , @Query("format") format :String ,@Query("num_of_days") num_of_days :String) : NetworkResponse<WeatherResponse, Response.ErrorResponse>



}