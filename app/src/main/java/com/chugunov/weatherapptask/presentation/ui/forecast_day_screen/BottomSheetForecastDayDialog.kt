package com.chugunov.weatherapptask.presentation.ui.forecast_day_screen

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.chugunov.weatherapptask.databinding.FragmentBottomSheetForecastDialogBinding
import com.chugunov.weatherapptask.domain.entities.weather_entities.Forecastday
import com.chugunov.weatherapptask.presentation.ui.adapters.HourItemAdapter
import com.chugunov.weatherapptask.presentation.utils.ConvertDateUtils
import com.chugunov.weatherapptask.presentation.utils.FormattedUrl
import com.chugunov.weatherapptask.presentation.utils.FormattedWeatherData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetForecastDayDialog : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetForecastDialogBinding? = null
    private val binding: FragmentBottomSheetForecastDialogBinding
        get() = _binding
            ?: throw RuntimeException("FragmentBottomSheetForecastDialogBinding = null")


    private lateinit var adapter: HourItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetForecastDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindWeatherData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun bindWeatherData() {
        val forecastDay = when {
            Build.VERSION.SDK_INT >= 33 -> arguments?.getParcelable(
                ARG_FORECAST_DAY,
                Forecastday::class.java
            )

            else -> @Suppress("DEPRECATION") arguments?.getParcelable(ARG_FORECAST_DAY) as? Forecastday
        }
        if (forecastDay != null) {
            with(binding) {
                imageWeatherStatus.load(FormattedUrl(forecastDay.day.condition.icon))
                tvTemperatureTitle.text =
                    FormattedWeatherData.formattedToCelsius(forecastDay.day.avgtemp_c)
                tvTempMeter.text =
                    FormattedWeatherData.formattedToCelsius(forecastDay.day.avgtemp_c)
                conditionText.text = forecastDay.day.condition.text
                dateTextview.text = ConvertDateUtils.convertDateToMonth(forecastDay.date)
                tvTempHumidity.text =
                    FormattedWeatherData.formattedToPercent(forecastDay.day.avghumidity.toInt())
                tvTempWind.text = FormattedWeatherData.formattedWind(forecastDay.day.avgvis_km)
                binding.hourRv.adapter = adapter
                adapter.submitList(forecastDay.hour)
            }

        }
    }

    companion object {
        private const val ARG_FORECAST_DAY = "forecastDay"
        fun newInstance(forecastDay: Forecastday): BottomSheetForecastDayDialog {
            val args = Bundle()
            args.putParcelable(ARG_FORECAST_DAY, forecastDay)
            val fragment = BottomSheetForecastDayDialog()
            fragment.adapter = HourItemAdapter()
            fragment.arguments = args
            return fragment
        }
    }
}