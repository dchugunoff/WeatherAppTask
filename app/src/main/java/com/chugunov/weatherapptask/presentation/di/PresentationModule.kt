package com.chugunov.weatherapptask.presentation.di

import com.chugunov.weatherapptask.presentation.ui.adapters.HourItemAdapter
import com.chugunov.weatherapptask.presentation.ui.adapters.WeatherForecastAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    @Singleton
    fun provideForecastAdapter(): WeatherForecastAdapter = WeatherForecastAdapter()

    @Provides
    fun provideHourAdapter(): HourItemAdapter = HourItemAdapter()
}