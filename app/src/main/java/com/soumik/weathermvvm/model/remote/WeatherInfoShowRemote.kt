package com.soumik.weathermvvm.model.remote

import com.soumik.weathermvvm.model.data_class.WeatherResponse
import com.soumik.weathermvvm.utils.RequestCompleteListener

interface WeatherInfoShowRemote {
    fun getWeatherInfo(cityID:Int,callBack:RequestCompleteListener<WeatherResponse>)
}