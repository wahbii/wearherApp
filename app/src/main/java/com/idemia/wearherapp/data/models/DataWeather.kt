package com.idemia.wearherapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class DataWeather(
    @SerializedName("request")
    var request : List<LocationData>,
    @SerializedName("current_condition")
    var currentCondition: List<CurrentCondition>,
    @SerializedName("weather")
    var weatherdetail : List<WeatherElements>,
    @SerializedName("ClimateAverages")
    var climateAverages :List<ClimateAverages>

): Serializable