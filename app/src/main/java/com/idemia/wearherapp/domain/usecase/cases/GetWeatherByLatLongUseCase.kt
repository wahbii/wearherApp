package com.idemia.wearherapp.domain.usecase.cases

import com.idemia.wearherapp.data.models.WeatherResponse
import com.idemia.wearherapp.domain.usecase.base.BaseUseCase

interface GetWeatherByLatLongUseCase : BaseUseCase<Any, WeatherResponse> {
}