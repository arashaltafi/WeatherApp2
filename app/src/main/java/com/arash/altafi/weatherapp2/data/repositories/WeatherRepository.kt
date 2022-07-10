package com.arash.altafi.weatherapp2.data.repositories

import com.arash.altafi.weatherapp2.data.local.WeatherDatabase
import com.arash.altafi.weatherapp2.data.model.WeatherDataResponse
import com.arash.altafi.weatherapp2.data.model.WeatherDetail
import com.arash.altafi.weatherapp2.data.network.ApiInterface
import com.arash.altafi.weatherapp2.data.network.SafeApiRequest

class WeatherRepository(
    private val api: ApiInterface,
    private val db: WeatherDatabase
) : SafeApiRequest() {

    suspend fun findCityWeather(cityName: String): WeatherDataResponse = apiRequest {
        api.findCityWeatherData(cityName)
    }

    suspend fun addWeather(weatherDetail: WeatherDetail) {
        db.getWeatherDao().addWeather(weatherDetail)
    }

    suspend fun fetchWeatherDetail(cityName: String): WeatherDetail? =
        db.getWeatherDao().fetchWeatherByCity(cityName)

    suspend fun fetchAllWeatherDetails(): List<WeatherDetail> =
        db.getWeatherDao().fetchAllWeatherDetails()
}
