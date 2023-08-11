package com.chugunov.weatherapptask.presentation.utils

class FormattedUrl {
    companion object {
        operator fun invoke(imageUrl: String): String {
            return imageUrl.replace("//", "https://")
        }
    }
}