package com.chugunov.weatherapptask.domain.entities.weather_entities

data class Current(
    val condition: Condition,
    val is_day: Int,
    val temp_c: Double,
    val temp_f: Double
)