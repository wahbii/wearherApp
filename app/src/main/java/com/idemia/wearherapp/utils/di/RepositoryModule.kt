package com.idemia.wearherapp.utils.di

import com.idemia.wearherapp.data.repository.WeatherRepositoryImpl
import com.idemia.wearherapp.data.source.network.api.ApiService
import com.idemia.wearherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    
    @Provides
    fun providesWeatherRepo(apiService: ApiService) : WeatherRepository = WeatherRepositoryImpl(apiService)
}