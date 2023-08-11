package com.chugunov.weatherapptask.domain.entities.weather_entities

data class Forecastday(
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
)