package com.soumik.weathermvvm.utils

import android.content.Context
import android.widget.Toast
import com.soumik.weathermvvm.model.data_class.CityResponse
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
const val APP_ID = "c1fac2c531c4c592942693d386edc753"

fun showToast(context: Context,message:String,length:Int) = Toast.makeText(context,message,length)

fun Int.unixTimestampToDateTimeString() : String {

    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this*1000.toLong()

        val outputDateFormat = SimpleDateFormat("dd MMM, yyyy - hh:mm a", Locale.ENGLISH)
        outputDateFormat.timeZone = TimeZone.getDefault() // user's default time zone
        return outputDateFormat.format(calendar.time)

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return this.toString()
}

fun Int.unixTimestampToTimeString() : String {

    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this*1000.toLong()

        val outputDateFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        outputDateFormat.timeZone = TimeZone.getDefault()
        return outputDateFormat.format(calendar.time)

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return this.toString()
}

fun ArrayList<CityResponse>.convertToListOfCityName() : MutableList<String> {

    val cityNameList: ArrayList<String> = arrayListOf()

    for (city in this) {
        cityNameList.add(city.name)
    }

    return  cityNameList
}

/**
 * The temperature T in degrees Celsius (°C) is equal to the temperature T in Kelvin (K) minus 273.15:
 * T(°C) = T(K) - 273.15
 *
 * Example
 * Convert 300 Kelvin to degrees Celsius:
 * T(°C) = 300K - 273.15 = 26.85 °C
 */
fun Double.kelvinToCelsius() : Int {

    return  (this - 273.15).toInt()
}