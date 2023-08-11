package com.idemia.wearherapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class WeatherElements(
    @SerializedName("date")
    var date: String,
    @SerializedName("astronomy")
    var astronomy: List<Astronomy>,
    @SerializedName("maxtempC")
    var maxtempC: Int,
    @SerializedName("mintempC")
    var mintempC: Int,
    @SerializedName("hourly")
    var hourly : List<Hourly>
    )