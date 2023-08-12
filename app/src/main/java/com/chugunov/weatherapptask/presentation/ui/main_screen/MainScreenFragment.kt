package com.chugunov.weatherapptask.presentation.ui.main_screen

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.chugunov.weatherapptask.R
import com.chugunov.weatherapptask.databinding.FragmentMainScreenBinding
import com.chugunov.weatherapptask.domain.entities.weather_entities.Forecastday
import com.chugunov.weatherapptask.presentation.ui.adapters.HourItemAdapter
import com.chugunov.weatherapptask.presentation.ui.adapters.WeatherForecastAdapter
import com.chugunov.weatherapptask.presentation.ui.viewmodels.WeatherViewModel
import com.chugunov.weatherapptask.presentation.ui.forecast_day_screen.BottomSheetForecastDayDialog
import com.chugunov.weatherapptask.presentation.utils.ConvertDateUtils
import com.chugunov.weatherapptask.presentation.utils.FormattedUrl
import com.chugunov.weatherapptask.presentation.utils.FormattedWeatherData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private val viewModel: WeatherViewModel by activityViewModels()

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
                tvTemperatureTitle.text = FormattedWeatherData.formattedToCelsius(it.current.temp_c)
                tvTempHumidity.text = FormattedWeatherData.formattedToPercent(it.current.humidity)
                tvTempWind.text = FormattedWeatherData.formattedWind(it.current.wind_kph)
                tvTempMeter.text = FormattedWeatherData.formattedToCelsius(it.current.temp_c)
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
            adapter.setOnItemClickListener(object : WeatherForecastAdapter.OnItemClickListener {
                override fun onItemClick(forecastDay: Forecastday) {
                    val bottomSheetDialog = BottomSheetForecastDayDialog.newInstance(forecastDay)
                    bottomSheetDialog.show(parentFragmentManager, bottomSheetDialog.tag)
                }

            })
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
}