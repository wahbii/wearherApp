package com.idemia.wearherapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Astronomy(
    @SerializedName("sunrise")
 var    sunrise :String,
    @SerializedName("sunset")
var  sunset : String,
    @SerializedName("moonrise")
var  moonrise : String,
    @SerializedName("moonset")
var  moonset : String
)
