package com.chugunov.weatherapptask.domain.entities.weather_entities

data class Current(
    val condition: Condition,
    val is_day: Int,
    val humidity: Int,
    val temp_c: Double,
    val temp_f: Double,
    val wind_kph: Double
)