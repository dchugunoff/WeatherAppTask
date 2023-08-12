package com.chugunov.weatherapptask.presentation.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chugunov.weatherapptask.databinding.CardForecastDayBinding
import com.chugunov.weatherapptask.domain.entities.weather_entities.Forecastday
import com.chugunov.weatherapptask.presentation.utils.ConvertDateUtils
import com.chugunov.weatherapptask.presentation.utils.FormattedUrl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherForecastAdapter @Inject constructor() :
    ListAdapter<Forecastday, WeatherForecastAdapter.WeatherForecastViewHolder>(DiffCallback) {

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class WeatherForecastViewHolder(private val binding: CardForecastDayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(forecastDay: Forecastday) {
            binding.weatherImage.load(FormattedUrl(forecastDay.day.condition.icon))
            binding.dayOfWeek.text = ConvertDateUtils.convertDateToDayOfWeek(forecastDay.date)
                .replaceFirstChar(Char::titlecase)
            binding.weatherTemp.text =
                "${forecastDay.day.maxtemp_c.toInt()} / ${forecastDay.day.mintemp_c.toInt()}"
        }

    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Forecastday>() {
            override fun areItemsTheSame(oldItem: Forecastday, newItem: Forecastday): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Forecastday, newItem: Forecastday): Boolean {
                return oldItem.date == newItem.date
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        val binding = CardForecastDayBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent,
            false
        )
        return WeatherForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        val forecastDay = getItem(position)
        holder.bind(forecastDay)
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(forecastDay)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(forecastDay: Forecastday)
    }
}
