package com.example.weathertestapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    //Тут хранится информация о текущем прогнозе погоды
    val liveDateCurrent = MutableLiveData<DayItem>()
    //Тут хранится информация о прогнозе погоды на следующие дни
    val liveDateList = MutableLiveData<List<DayItem>>()
}