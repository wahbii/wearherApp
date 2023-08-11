package com.idemia.wearherapp.data.models

import com.google.gson.annotations.SerializedName

data class Months(
    @SerializedName("index")
    var index: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("avgMinTemp")
    var avgMinTemp: Double,
    @SerializedName("avgMinTemp_F")
    var avgMinTemp_F: Double,
    @SerializedName("absMaxTemp")
    var absMaxTemp: Double,
    @SerializedName("absMaxTemp_F")
    var absMaxTemp_F: Double,
    @SerializedName("avgDailyRainfall")
    var avgDailyRainfall: Double


)


