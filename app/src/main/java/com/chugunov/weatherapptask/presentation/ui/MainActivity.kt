package com.chugunov.weatherapptask.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.chugunov.weatherapptask.R
import com.chugunov.weatherapptask.presentation.ui.main_screen.MainScreenFragment
import com.chugunov.weatherapptask.presentation.ui.viewmodels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.weatherData.observe(this) {
            if (it != null) {
                showWeatherScreen()
            }
        }
    }

    private fun showWeatherScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_container, MainScreenFragment())
            .commit()
    }
}