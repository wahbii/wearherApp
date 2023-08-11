package com.idemia.wearherapp.data.models

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class WeatherResponse(
    @SerializedName("data")
    var data : DataWeather
): Serializable