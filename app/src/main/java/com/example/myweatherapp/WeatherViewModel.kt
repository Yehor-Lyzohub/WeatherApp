package com.example.myweatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.network.DetailData
import com.example.myweatherapp.network.WeatherApi
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

enum class WeatherApiStatus { LOADING, ERROR, DONE }

class WeatherViewModel : ViewModel() {

    private val _cities = listOf(
        City("Kyiv"),
        City("Kharkiv"),
        City("Odessa"),
        City("Dnipro"),
        City("Donetsk"),
        City("Zaporizhzhia"),
        City("Lviv"),
        City("Kryvyi Rih"),
        City("Mykolaiv"),
        City("Mariupol")
    )
    val cities = _cities

    private val _dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("E d MMMM h:mm a"))
    val dateTime = _dateTime

    private val _status = MutableLiveData<WeatherApiStatus>()
    val status: LiveData<WeatherApiStatus> = _status

    private val _data = MutableLiveData<DetailData>()
    val data: LiveData<DetailData> = _data

    fun getDetailData(city: String) {
        viewModelScope.launch {
            _status.value = WeatherApiStatus.LOADING
            try {
                _data.value = WeatherApi.retrofitService.getData(city)
                _status.value = WeatherApiStatus.DONE
            } catch (e: Exception) {
                _status.value = WeatherApiStatus.ERROR
            }
        }
    }
}