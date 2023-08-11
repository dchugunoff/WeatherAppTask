package com.chugunov.weatherapptask.domain.entities.weather_entities

data class Day(
    val avgtemp_c: Double,
    val avgtemp_f: Double,
    val condition: Condition,
    val daily_chance_of_rain: Int,
    val daily_chance_of_snow: Int,
    val daily_will_it_rain: Int,
    val daily_will_it_snow: Int,
    val maxtemp_c: Double,
    val maxtemp_f: Double,
    val mintemp_c: Double,
    val mintemp_f: Double
)