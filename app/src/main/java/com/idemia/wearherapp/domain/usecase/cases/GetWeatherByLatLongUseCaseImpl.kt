package com.idemia.wearherapp.domain.usecase.cases

import com.idemia.wearherapp.data.models.WeatherResponse
import com.idemia.wearherapp.data.source.network.response.Resource
import com.idemia.wearherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetWeatherByLatLongUseCaseImpl @Inject constructor(val repository: WeatherRepository): GetWeatherByLatLongUseCase{
    override suspend fun invoke(param: Any): Flow<Resource<WeatherResponse>> {
        var data = param as String


        return flowOf(repository.getWeatherByLatLong("${data.split(",")[0] },${data.split(",")[1] }",data.split(",")[2])
        )
    }
}