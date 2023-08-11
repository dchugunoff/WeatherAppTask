package com.chugunov.weatherapptask.domain.entities.weather_entities

data class WeatherModel (
    val current: Current,
    val forecast: Forecast,
    val location: Location
)