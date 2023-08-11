package com.chugunov.weatherapptask.domain.repository

import com.chugunov.weatherapptask.domain.entities.weather_entities.WeatherModel

interface WeatherRepository {

    suspend fun getWeatherResponse(location: String, lang: String): WeatherModel
}