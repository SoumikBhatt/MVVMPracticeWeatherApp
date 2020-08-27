package com.soumik.weathermvvm.network

import com.soumik.weathermvvm.model.data_class.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("weather")
    fun callWeatherApi(@Query("id") cityID:Int):Call<WeatherResponse>
}