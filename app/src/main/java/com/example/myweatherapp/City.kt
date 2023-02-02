package com.example.myweatherapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City (
    val location: String) : Parcelable {
}