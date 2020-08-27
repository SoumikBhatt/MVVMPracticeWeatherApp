package com.soumik.weathermvvm.model.local

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.soumik.weathermvvm.model.data_class.CityResponse
import com.soumik.weathermvvm.utils.RequestCompleteListener

class CityListShowLocalImpl(private var mContext:Context):CityListShowLocal {
    override fun getCityList(callBack: RequestCompleteListener<ArrayList<CityResponse>>) {

        try {
            val stream = mContext.assets.open("city_list.json")

            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()

            val contents = String(buffer)

            val listType = object : TypeToken<ArrayList<CityResponse>>(){}.type
            val cityList:ArrayList<CityResponse> = GsonBuilder().create().fromJson(contents,listType)

            callBack.onRequestCompleted(cityList)

        } catch (e:Exception){
            e.printStackTrace()
            callBack.onRequestFailed(e.localizedMessage!!)
        }
    }
}
