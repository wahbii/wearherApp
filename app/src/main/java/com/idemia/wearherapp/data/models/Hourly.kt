package com.idemia.wearherapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Hourly(
    @SerializedName("time")
    var time: Int,
    @SerializedName("tempC")
    var tempC: Int,
    @SerializedName("tempF")
    var tempF: Int,
    @SerializedName("windspeedMiles")
    var windspeedMiles: Double,
    @SerializedName("windspeedKmph")
    var windspeedKmph: Double,
    @SerializedName("winddirDegree")
    var winddirDegree: Double,
    @SerializedName("winddir16Point")
    var winddir16Point: String,
    @SerializedName("weatherCode")
    var weatherCode: Double,
    @SerializedName("weatherIconUrl")
    var weatherIconUrl: List<Cdata>,
    @SerializedName("weatherDesc")
    var weatherDesc: List<Cdata>,
    @SerializedName("precipMM")
    var precipMM: Double,
    @SerializedName("humidity")
    var humidity: Double,
    @SerializedName("visibility")
    var visibility: Double,
    @SerializedName("pressure")
    var pressure: Double,
    @SerializedName("cloudcover")
    var cloudcover: Double,
    @SerializedName("chanceofrain")
    var chanceofrain: Double,
    @SerializedName("chanceofovercast")
    var chanceofovercast: Double,
    @SerializedName("chanceofsnow")
    var chanceofsnow: Double,
    @SerializedName("chanceofthunder")
    var chanceofthunder: Double
)


