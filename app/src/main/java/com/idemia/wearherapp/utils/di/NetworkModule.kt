package com.idemia.wearherapp.utils.di

import com.google.gson.GsonBuilder
import com.idemia.wearherapp.BuildConfig
import com.idemia.wearherapp.data.source.network.api.ApiService
import com.idemia.wearherapp.data.source.network.response.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class NetworkModule {

    @Provides
    fun providesOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    fun providesGsonConverterFactory(): Converter.Factory{
        return GsonConverterFactory.create(
            GsonBuilder()
                .create()
                )
    }




    @Provides
    @Singleton
    fun providesRetrofitApi(okHttpClient: OkHttpClient, converterFactory : Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)//BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())// to make callAdapter work with coroutines
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesIApiService(retrofit: Retrofit): ApiService {
        return retrofit.create()
    }

    @Provides
    fun providesOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder,
                             httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        if (BuildConfig.BUILD_TYPE != "release") {

            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        return okHttpClientBuilder
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .hostnameVerifier { _, _-> true }
            .build()
    }
}