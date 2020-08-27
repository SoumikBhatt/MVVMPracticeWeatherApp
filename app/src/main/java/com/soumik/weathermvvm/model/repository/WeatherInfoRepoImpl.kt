package com.soumik.weathermvvm.model.repository

import android.content.Context
import com.soumik.weathermvvm.model.data_class.CityResponse
import com.soumik.weathermvvm.model.data_class.WeatherResponse
import com.soumik.weathermvvm.model.local.CityListShowLocalImpl
import com.soumik.weathermvvm.model.remote.WeatherInfoShowRemoteImpl
import com.soumik.weathermvvm.utils.RequestCompleteListener

class WeatherInfoRepoImpl(private var mContext:Context):WeatherInfoRepo {
    var cityListShowLocalImpl = CityListShowLocalImpl(mContext)
    var weatherInfoShowRemoteImpl = WeatherInfoShowRemoteImpl(mContext)

    override fun getCityList(callBack: RequestCompleteListener<ArrayList<CityResponse>>) {
        cityListShowLocalImpl.getCityList(callBack)
    }

    override fun getWeatherInfo(cityID: Int, callBack: RequestCompleteListener<WeatherResponse>) {
        weatherInfoShowRemoteImpl.getWeatherInfo(cityID, callBack)
    }
}