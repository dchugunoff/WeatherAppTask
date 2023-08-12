package com.chugunov.weatherapptask.presentation.utils

class FormattedWeatherData {
    companion object {
        fun formattedToCelsius(celsius: Double): String {
            return "${celsius.toInt()}°C"
        }

        fun formattedToPercent(humidity: Int): String {
            return "$humidity%"
        }

        fun formattedWind(wind: Double): String {
            val metersPerSecond = (1000.0 / 3600) * wind
            val format = String.format("%.1f", metersPerSecond)
            return "$format м/c"
        }
    }
}