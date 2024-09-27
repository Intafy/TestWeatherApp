package com.example.weathertestapp

data class DayItem(
    val city:String,
    val time:String,
    val condition:String,
    val currentTemp:String,
    val imageUrl:String,
    val maxTemp:String,
    val minTemp:String,
    val humidity:String,
    val windDirection:String,
    val windSpeed:String
)

