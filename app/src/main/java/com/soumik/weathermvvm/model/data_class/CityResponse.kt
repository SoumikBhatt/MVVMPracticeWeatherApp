package com.soumik.weathermvvm.model.data_class
import com.google.gson.annotations.SerializedName


data class CityResponse(
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
