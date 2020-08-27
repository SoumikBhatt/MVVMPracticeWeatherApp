package com.soumik.weathermvvm.model.repository

import com.soumik.weathermvvm.model.data_class.CityResponse
import com.soumik.weathermvvm.model.data_class.WeatherResponse
import com.soumik.weathermvvm.utils.RequestCompleteListener

interface WeatherInfoRepo {
    fun getCityList(callBack: RequestCompleteListener<ArrayList<CityResponse>>)
    fun getWeatherInfo(cityID:Int,callBack: RequestCompleteListener<WeatherResponse>)
}