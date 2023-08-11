package com.idemia.wearherapp.utils.di

import com.idemia.wearherapp.domain.repository.WeatherRepository
import com.idemia.wearherapp.domain.usecase.cases.GetWeatherByLatLongUseCase
import com.idemia.wearherapp.domain.usecase.cases.GetWeatherByLatLongUseCaseImpl
import com.idemia.wearherapp.domain.usecase.cases.GetWeatherByLocationUseCase
import com.idemia.wearherapp.domain.usecase.cases.GetWeatherByLocationUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    fun providesUseCaseWeatherByLocation(repository: WeatherRepository) : GetWeatherByLocationUseCase = GetWeatherByLocationUseCaseImpl(repository)


    @Provides
    fun providesUseCaseWeatherByLatLong(repository: WeatherRepository) : GetWeatherByLatLongUseCase  = GetWeatherByLatLongUseCaseImpl(repository)
}