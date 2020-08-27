package com.soumik.weathermvvm.model.remote

import android.content.Context
import com.soumik.weathermvvm.model.data_class.WeatherResponse
import com.soumik.weathermvvm.network.apiClient
import com.soumik.weathermvvm.utils.RequestCompleteListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherInfoShowRemoteImpl(private var mContext:Context):WeatherInfoShowRemote {
    override fun getWeatherInfo(cityID: Int, callBack: RequestCompleteListener<WeatherResponse>) {
        apiClient.callWeatherApi(cityID).enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful && response.body()!=null) callBack.onRequestCompleted(response.body()!!)
                else callBack.onRequestFailed(response.message())
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                callBack.onRequestFailed(t.localizedMessage!!)
            }
        })
    }
}