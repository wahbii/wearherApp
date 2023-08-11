package com.idemia.wearherapp.domain.usecase.cases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.idemia.wearherapp.data.models.WeatherResponse
import com.idemia.wearherapp.data.source.network.response.Resource
import com.idemia.wearherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetWeatherByLocationUseCaseImpl @Inject constructor(val repository: WeatherRepository): GetWeatherByLocationUseCase{
    override suspend fun invoke(param: Any): Flow<Resource<WeatherResponse>> {
        var data = param as String


        return flowOf(repository.getWeatherByCity(data.split(",")[0],data.split(",")[1])
        )
    }
}