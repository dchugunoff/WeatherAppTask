package com.chugunov.weatherapptask.presentation.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.chugunov.weatherapptask.R
import com.chugunov.weatherapptask.databinding.FragmentMainScreenBinding
import com.chugunov.weatherapptask.presentation.utils.ConvertDateUtils
import com.chugunov.weatherapptask.presentation.utils.FormattedUrl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModels()

    @Inject
    lateinit var adapter: WeatherForecastAdapter

    @Inject
    lateinit var hourAdapter: HourItemAdapter

    private var _binding: FragmentMainScreenBinding? = null
    private val binding: FragmentMainScreenBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        bindCurrentWeather()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindAdapters()
        binding.citySelector.setOnClickListener {
            showCitySelectorDialog()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun bindCurrentWeather() {
        viewModel.weatherData.observe(viewLifecycleOwner) {
            with(binding) {
                tvCityName.text = it.location.name
                tvTemperatureTitle.text = formattedToCelsius(it.current.temp_c)
                tvTempHumidity.text = formattedToPercent(it.current.humidity)
                tvTempWind.text = formattedWind(it.current.wind_kph)
                tvTempMeter.text = formattedToCelsius(it.current.temp_c)
                imageWeatherStatus.load(FormattedUrl(it.current.condition.icon))
                conditionText.text = it.current.condition.text
                dateTextview.text =
                    ConvertDateUtils.convertDateToMonth(it.forecast.forecastday[0].date)
            }
        }
    }

    private fun bindAdapters() {
        viewModel.weatherData.observe(viewLifecycleOwner) {
            binding.forecastWeatherRv.adapter = adapter
            binding.todayRv.adapter = hourAdapter
            viewModel.weatherData.observe(viewLifecycleOwner) {
                adapter.submitList(it.forecast.forecastday.drop(1))
                hourAdapter.submitList(it.forecast.forecastday[0].hour)
            }
        }
    }

    private fun showCitySelectorDialog() {
        val cityNames = resources.getStringArray(R.array.city_array)
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.select_city))
        builder.setItems(cityNames) { _, which ->
            val selectedCity = cityNames[which]
            viewModel.updateWeatherData(selectedCity)
        }
        val dialog = builder.create()
        dialog.show()
    }


    private fun formattedToCelsius(celsius: Double): String {
        val temp = celsius.toInt()
        return String.format(getString(R.string.formatted_temp), temp)
    }

    private fun formattedToPercent(humidity: Int): String {
        return String.format(getString(R.string.formatted_humidity), humidity)
    }

    private fun formattedWind(wind: Double): String {
        val metersPerSecond = (1000.0 / 3600) * wind
        val format = String.format("%.1f", metersPerSecond)
        Log.d("MainScreenFragment", metersPerSecond.toString())
        return String.format(getString(R.string.formatted_wind), format)
    }
}