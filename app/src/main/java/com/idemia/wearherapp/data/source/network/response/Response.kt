package com.idemia.wearherapp.data.source.network.response
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Response {
    @Parcelize
    class BaseResponse(val code: Int, val message: String) : Response(), Parcelable






    @Parcelize
    class ErrorResponse(val id: Int, val message: String) : Response(), Parcelable


}

