package com.example.myweatherapp.network

data class Weather(
    val weather: List<WeatherX>
)

data class WeatherX(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)