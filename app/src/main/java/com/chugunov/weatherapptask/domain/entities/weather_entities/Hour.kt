package com.chugunov.weatherapptask.domain.entities.weather_entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hour(
    val chance_of_rain: Int,
    val chance_of_snow: Int,
    val condition: Condition,
    val dewpoint_c: Double,
    val dewpoint_f: Double,
    val is_day: Int,
    val temp_c: Double,
    val temp_f: Double,
    val time: String,
    val time_epoch: Int
): Parcelable