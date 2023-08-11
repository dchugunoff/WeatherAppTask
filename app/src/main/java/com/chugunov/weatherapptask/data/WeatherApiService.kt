package com.chugunov.weatherapptask.data

import com.chugunov.weatherapptask.domain.entities.weather_entities.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast.json")
    suspend fun getWeatherResponse(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("lang") language: String = "ru",
        @Query("aqi") aqi: String = "no",
        @Query("days") days: Int = 5
    ): WeatherModel
}