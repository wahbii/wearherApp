package com.idemia.wearherapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class LocationData(
    @SerializedName("type")
    var type :String,
    @SerializedName("query")
    var query : String,
)