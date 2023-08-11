package com.chugunov.weatherapptask.data.repository

import com.chugunov.weatherapptask.data.WeatherApiService
import com.chugunov.weatherapptask.domain.entities.weather_entities.WeatherModel
import com.chugunov.weatherapptask.domain.repository.WeatherRepository
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(private val weatherApiService: WeatherApiService) :
    WeatherRepository {
    override suspend fun getWeatherResponse(
        location: String,
        lang: String,
    ): WeatherModel {
        val apiKey = "9488e3c318bf4f17a53145908230108"
        return weatherApiService.getWeatherResponse(
            apiKey = apiKey,
            location = location,
            language = lang,
        )
    }

}