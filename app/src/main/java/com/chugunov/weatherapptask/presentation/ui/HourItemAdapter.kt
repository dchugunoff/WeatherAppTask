package com.chugunov.weatherapptask.presentation.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chugunov.weatherapptask.databinding.CardHourItemBinding
import com.chugunov.weatherapptask.domain.entities.weather_entities.Hour
import com.chugunov.weatherapptask.presentation.utils.ConvertDateUtils
import com.chugunov.weatherapptask.presentation.utils.FormattedUrl

class HourItemAdapter() : ListAdapter<Hour, HourItemAdapter.HourItemViewHolder>(DiffCallback) {
    inner class HourItemViewHolder(private val binding: CardHourItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(hourItem: Hour) {
                with(binding) {
                    hour.text = ConvertDateUtils.convertDateTimeOnly(hourItem.time)
                    hourTemp.text = "${hourItem.temp_c.toInt()}°C"
                    hourImageCondition.load(FormattedUrl(hourItem.condition.icon))
                }
            }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourItemViewHolder {
        val binding = CardHourItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HourItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourItemViewHolder, position: Int) {
        val hourItem = getItem(position)
        holder.bind(hourItem)
    }
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Hour>() {
            override fun areItemsTheSame(oldItem: Hour, newItem: Hour): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Hour, newItem: Hour): Boolean {
                return oldItem.time == newItem.time
            }

        }
    }
}