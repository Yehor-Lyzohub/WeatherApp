package com.example.myweatherapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val KEY = "b2d1c112ae95edb8962b4288bb5c3567"
private const val REQUEST = "weather?&appid=${KEY}&units=metric"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WeatherAPIService {
    @GET(REQUEST)
    suspend fun getData(@Query("q") city: String) : DetailData
}

object WeatherApi {
    val retrofitService: WeatherAPIService by lazy { retrofit.create(WeatherAPIService::class.java) }
}