package com.idemia.wearherapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class CurrentCondition(
    @SerializedName("observation_time")
    var observation_time: String,
    @SerializedName("temp_C")
    var temp_C: String,
    @SerializedName("weatherCode")
    var weatherCode: String,
    @SerializedName("weatherIconUrl")
    var weatherIconUrl: List<Cdata>,
    @SerializedName("weatherDesc")
    var weatherDesc: List<Cdata>,
    @SerializedName("windspeedMiles")
    var windspeedMiles:  Double,
    @SerializedName("windspeedKmph")
    var windspeedKmph:  Double,
    @SerializedName("winddirDegree")
    var winddirDegree: Double,
    @SerializedName("winddir16Point")
    var winddir16Point: String,
    @SerializedName("precipMM")
    var precipMM:  Double,
    @SerializedName("humidity")
    var humidity:  Double,
    @SerializedName("visibility")
    var visibility:  Double,
    @SerializedName("pressure")
    var pressure:  Double,
    @SerializedName("cloudcover")
    var cloudcover:  Double
)


