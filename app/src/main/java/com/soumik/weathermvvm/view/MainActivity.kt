package com.soumik.weathermvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.soumik.weathermvvm.R
import com.soumik.weathermvvm.model.data_class.CityResponse
import com.soumik.weathermvvm.model.data_class.WeatherData
import com.soumik.weathermvvm.model.repository.WeatherInfoRepo
import com.soumik.weathermvvm.model.repository.WeatherInfoRepoImpl
import com.soumik.weathermvvm.utils.convertToListOfCityName
import com.soumik.weathermvvm.utils.showToast
import com.soumik.weathermvvm.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_input_part.*
import kotlinx.android.synthetic.main.layout_sunrise_sunset.*
import kotlinx.android.synthetic.main.layout_weather_additional_info.*
import kotlinx.android.synthetic.main.layout_weather_basic_info.*

class MainActivity : AppCompatActivity() {

    private lateinit var model:WeatherInfoRepo
    private lateinit var viewModel: WeatherViewModel

    private var cityList:ArrayList<CityResponse>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = WeatherInfoRepoImpl(applicationContext)
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        setLiveDataListeners()

        viewModel.getCityList(model)

    }

    private fun setLiveDataListeners() {
        //cityListFetched Successfully
        viewModel.cityListLiveData.observe(this, Observer { cityList ->
            setCityListToSpinner(cityList)
        })

        //cityListFetch Failed
        viewModel.cityListFailureLiveData.observe(this,Observer{ errorMessage ->
            showToast(applicationContext,errorMessage,1)
        })

        //progress bar handling
        viewModel.progressLiveData.observe(this, Observer{ isShowLoader ->
            if (isShowLoader) progressBar.visibility=View.VISIBLE
            else progressBar.visibility=View.GONE
        })

        //weatherInfo fetched successfully
        viewModel.weatherInfoLiveData.observe(this,Observer { weatherData ->
            setWeatherInfo(weatherData)
        })

        viewModel.weatherInfoFailureLiveData.observe(this, Observer { errorMessage ->
            output_group.visibility = View.GONE
            tv_error_message.visibility = View.VISIBLE
            tv_error_message.text = errorMessage
        })
    }

    private fun setWeatherInfo(weatherData: WeatherData?) {
        output_group.visibility = View.VISIBLE
        tv_error_message.visibility = View.GONE

        tv_date_time?.text = weatherData?.dateTime
        tv_temperature?.text = weatherData?.temperature
        tv_city_country?.text = weatherData?.cityAndCountry
        Glide.with(this).load(weatherData?.weatherConditionIconUrl).into(iv_weather_condition)
        tv_weather_condition?.text = weatherData?.weatherConditionIconDescription

        tv_humidity_value?.text = weatherData?.humidity
        tv_pressure_value?.text = weatherData?.pressure
        tv_visibility_value?.text = weatherData?.visibility

        tv_sunrise_time?.text = weatherData?.sunrise
        tv_sunset_time?.text = weatherData?.sunset
    }

    private fun setCityListToSpinner(cityList: ArrayList<CityResponse>?) {

        this.cityList = cityList

        val arrayAdapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,this.cityList!!.convertToListOfCityName())

        spinner.adapter = arrayAdapter
    }

    fun onViewWeatherClicked(view: View) {
        val selectedCityID = cityList!![spinner.selectedItemPosition].id
        viewModel.getWeatherInfo(selectedCityID,model)
    }
}