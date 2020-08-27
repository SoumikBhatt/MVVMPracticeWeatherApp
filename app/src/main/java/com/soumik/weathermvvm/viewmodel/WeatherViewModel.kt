package com.soumik.weathermvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.soumik.weathermvvm.model.data_class.CityResponse
import com.soumik.weathermvvm.model.data_class.WeatherData
import com.soumik.weathermvvm.model.data_class.WeatherResponse
import com.soumik.weathermvvm.model.repository.WeatherInfoRepo
import com.soumik.weathermvvm.utils.RequestCompleteListener
import com.soumik.weathermvvm.utils.kelvinToCelsius
import com.soumik.weathermvvm.utils.unixTimestampToDateTimeString
import com.soumik.weathermvvm.utils.unixTimestampToTimeString

class WeatherViewModel:ViewModel() {

    // live data for city list
    val cityListLiveData = MutableLiveData<ArrayList<CityResponse>>()
    val cityListFailureLiveData = MutableLiveData<String>()

    // live data for weather info
    val weatherInfoLiveData = MutableLiveData<WeatherData>()
    val weatherInfoFailureLiveData = MutableLiveData<String>()

    //live data for progress
    val progressLiveData = MutableLiveData<Boolean>()

    fun getCityList(model: WeatherInfoRepo){
        progressLiveData.postValue(true)
        model.getCityList(object : RequestCompleteListener<ArrayList<CityResponse>>{
            override fun onRequestCompleted(data: ArrayList<CityResponse>) {
                cityListLiveData.postValue(data)
            }

            override fun onRequestFailed(errorMessage: String) {
                cityListFailureLiveData.postValue(errorMessage)
            }
        })
    }

    fun getWeatherInfo(cityId:Int,model: WeatherInfoRepo){
        model.getWeatherInfo(cityId,object : RequestCompleteListener<WeatherResponse>{
            override fun onRequestCompleted(data: WeatherResponse) {

                val weatherData = WeatherData (
                    dateTime = data.dt.unixTimestampToDateTimeString(),
                    temperature = data.main.temp.kelvinToCelsius().toString(),
                    cityAndCountry = "${data.name}, ${data.sys.country}",
                    weatherConditionIconUrl = "http://openweathermap.org/img/w/${data.weather[0].icon}.png",
                    weatherConditionIconDescription = data.weather[0].description,
                    humidity = "${data.main.humidity}%",
                    pressure = "${data.main.pressure} mBar",
                    visibility = "${data.visibility/1000.0} KM",
                    sunrise = data.sys.sunrise.unixTimestampToTimeString(),
                    sunset = data.sys.sunset.unixTimestampToTimeString()
                )

                progressLiveData.postValue(false)
                weatherInfoLiveData.postValue(weatherData)
            }

            override fun onRequestFailed(errorMessage: String) {
                progressLiveData.postValue(false)
                weatherInfoFailureLiveData.postValue(errorMessage)
            }
        })
    }
}