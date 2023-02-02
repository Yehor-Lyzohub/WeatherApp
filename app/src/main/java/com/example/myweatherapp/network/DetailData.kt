package com.example.myweatherapp.network

data class DetailData(
    val main: Main,
    val weather: List<Weather>
)