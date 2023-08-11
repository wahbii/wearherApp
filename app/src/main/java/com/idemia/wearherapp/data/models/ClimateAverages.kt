package com.idemia.wearherapp.data.models

import com.google.gson.annotations.SerializedName

data class ClimateAverages(
    @SerializedName("month")
    var month : List<Months>,
)


