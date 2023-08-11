package com.idemia.wearherapp.presentation.weatherhome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idemia.wearherapp.data.models.WeatherResponse
import com.idemia.wearherapp.data.source.network.response.Resource
import com.idemia.wearherapp.data.source.network.response.Response
import com.idemia.wearherapp.domain.usecase.cases.GetWeatherByLatLongUseCase
import com.idemia.wearherapp.domain.usecase.cases.GetWeatherByLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
   val getWeatherByLocationUseCase: GetWeatherByLocationUseCase,
   val getWeatherByLatLongUseCase: GetWeatherByLatLongUseCase
) : ViewModel() {

    private var _weather = MutableLiveData<WeatherResponse>()
    val weather : LiveData<WeatherResponse> = _weather


    private var _cityLatlong = MutableLiveData<WeatherResponse>()
    val cityLatlong : LiveData<WeatherResponse> = _cityLatlong


    private var _error = MutableLiveData<Response.ErrorResponse>()
    val error : LiveData<Response.ErrorResponse> = _error


    fun  getWeatherByLocation(location :String , days :String){

       CoroutineScope(Dispatchers.IO).launch{
           var data =   getWeatherByLocationUseCase.invoke("$location,$days")
           withContext(Dispatchers.Main){
               data.collect{
                       result ->
                   when (result) {
                       is Resource.Success -> {
                           _weather.value = result.data
                       }
                       is Resource.Error -> {
                           _error.value = result.message?.let { Response.ErrorResponse(result.code, it) }
                       }
                   }
               }
           }
       }
    }


    fun getLocationLatLong(lat :Double , long :Double , days: String){
        CoroutineScope(Dispatchers.IO).launch{
        var data =   getWeatherByLatLongUseCase.invoke("$lat,$long,$days")
        withContext(Dispatchers.Main){
            data.collect{
                    result ->
                when (result) {
                    is Resource.Success -> {
                        _cityLatlong.value = result.data
                    }
                    is Resource.Error -> {
                        _error.value = result.message?.let { Response.ErrorResponse(result.code, it) }
                    }
                }
            }
        }
    }
    }



}