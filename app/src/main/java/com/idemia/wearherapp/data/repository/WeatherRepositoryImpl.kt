package com.idemia.wearherapp.data.repository

import com.idemia.wearherapp.domain.repository.WeatherRepository
import com.idemia.wearherapp.BuildConfig
import com.idemia.wearherapp.data.models.WeatherResponse
import com.idemia.wearherapp.data.source.network.api.ApiService
import com.idemia.wearherapp.data.source.network.response.NetworkResponse
import com.idemia.wearherapp.data.source.network.response.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(val apiService: ApiService) : WeatherRepository{
    override suspend fun getWeatherByCity(location: String, days: String): Resource<WeatherResponse> {
        return when (val response =
            apiService.getWeatherbyCity(location,BuildConfig.APIKEY,"json",days)) {
            is NetworkResponse.Success -> {
               var weather = response.body!!

                return Resource.Success(weather)
            }
            is NetworkResponse.ApiError -> {

                Resource.Error(response.code, response.body.message)
            }
            is NetworkResponse.NetworkError -> {
                Resource.Error(2000, "${response.error}")
            }
            else -> {
                Resource.Error(1000, "$response")
            }
        }
    }

    override suspend fun getWeatherByLatLong(
        location: String,
        days: String
    ): Resource<WeatherResponse> {
        return when (val response =
            apiService.getWeatherbyLatLong(location,BuildConfig.APIKEY,"json",days)) {
            is NetworkResponse.Success -> {
                var weather = response.body!!

                return Resource.Success(weather)
            }
            is NetworkResponse.ApiError -> {

                Resource.Error(response.code, response.body.message)
            }
            is NetworkResponse.NetworkError -> {
                Resource.Error(2000, "${response.error}")
            }
            else -> {
                Resource.Error(1000, "$response")
            }
        }
    }
}