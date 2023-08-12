package com.chugunov.weatherapptask.presentation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chugunov.weatherapptask.data.repository.WeatherRepositoryImpl
import com.chugunov.weatherapptask.domain.entities.weather_entities.WeatherModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepositoryImpl: WeatherRepositoryImpl
): ViewModel() {

    private val _weatherData = MutableLiveData<WeatherModel>()
    val weatherData: LiveData<WeatherModel>
        get() = _weatherData

    init {
        updateWeatherData()
    }

    private fun updateWeatherData() {
        viewModelScope.launch {
            try {
                val weather = weatherRepositoryImpl.getWeatherResponse(DEFAULT_CITY_NAME, LANG)
                _weatherData.value = weather
                Log.d("WeatherViewModel", "${_weatherData.value.toString()}")
            } catch (e: Exception) {
                Log.d("WeatherViewModel", "Error fetching weather data ${e.message}")
            }
        }
    }

    companion object {
        const val LANG = "ru"
        const val DEFAULT_CITY_NAME = "Volgograd"
    }
}