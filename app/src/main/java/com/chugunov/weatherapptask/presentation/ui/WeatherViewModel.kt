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
        updateWeatherData(city = DEFAULT_CITY_NAME)
    }

    fun updateWeatherData(city: String) {
        viewModelScope.launch {
            try {
                val weather = weatherRepositoryImpl.getWeatherResponse(city)
                _weatherData.value = weather
            } catch (e: Exception) {
                Log.d("WeatherViewModel", "Error fetching weather data ${e.message}")
            }
        }
    }

    companion object {
        const val DEFAULT_CITY_NAME = "Москва"
    }
}