package com.soumik.weathermvvm.model.local

import com.soumik.weathermvvm.model.data_class.CityResponse
import com.soumik.weathermvvm.utils.RequestCompleteListener

interface CityListShowLocal {
    fun getCityList(callBack: RequestCompleteListener<ArrayList<CityResponse>>)
}