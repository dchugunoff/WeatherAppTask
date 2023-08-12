package com.chugunov.weatherapptask.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chugunov.weatherapptask.databinding.FragmentBottomSheetForecastDialogBinding
import com.chugunov.weatherapptask.domain.entities.weather_entities.Forecastday
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetForecastDayDialog : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetForecastDialogBinding? = null
    private val binding: FragmentBottomSheetForecastDialogBinding
        get() = _binding
            ?: throw RuntimeException("FragmentBottomSheetForecastDialogBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetForecastDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val ARG_FORECAST_DAY = "forecastDay"
        fun newInstance(forecastDay: Forecastday): BottomSheetForecastDayDialog {
            val args = Bundle()
            args.putParcelable(ARG_FORECAST_DAY, forecastDay)
            val fragment = BottomSheetForecastDayDialog()
            fragment.arguments = args
            return fragment
        }
    }
}