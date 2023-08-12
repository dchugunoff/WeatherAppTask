package com.chugunov.weatherapptask.domain.entities.weather_entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecastday(
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
): Parcelable