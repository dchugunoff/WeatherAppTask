package com.chugunov.weatherapptask.data.di


import com.chugunov.weatherapptask.data.WeatherApiService
import com.chugunov.weatherapptask.data.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object WeatherNetworkModule {

    private const val BASE_URL = "https://api.weatherapi.com/v1/"

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    fun provideWeatherRepository(weatherApiService: WeatherApiService): WeatherRepositoryImpl {
        return WeatherRepositoryImpl(weatherApiService)
    }


}
