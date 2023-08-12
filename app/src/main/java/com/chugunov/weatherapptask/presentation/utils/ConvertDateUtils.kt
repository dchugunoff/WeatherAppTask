package com.chugunov.weatherapptask.presentation.utils

import java.text.SimpleDateFormat
import java.util.Locale

class ConvertDateUtils {

    companion object {
        fun convertDateTimeOnly(dateTime: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            return try {
                val date = inputFormat.parse(dateTime)
                outputFormat.format(date)
            } catch (e: Exception) {
                ""
            }
        }

        fun convertDateToMonth(date: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("MMMM, dd", Locale.getDefault())
            return try {
                val dateParse = inputFormat.parse(date)
                outputFormat.format(dateParse)
            } catch (e: Exception) {
                ""
            }
        }

        fun convertDateToDayOfWeek(date: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("EEEE", Locale.getDefault())
            return try {
                val dateParse = inputFormat.parse(date)
                outputFormat.format(dateParse)
            } catch (e: Exception) {
                ""
            }
        }
    }
}