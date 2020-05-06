package com.quantrics.finch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quantrics.finch.global.base.BaseModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StopTimeModel(

    @Expose
    @SerializedName("service_id")
    var serviceId: Int = 0,

    @Expose
    @SerializedName("shape")
    var shape: String = "",

    @Expose
    @SerializedName("departure_time")
    var departureTime: String = "",

    @Expose
    @SerializedName("departure_timestamp")
    var departureTimestamp: Long = 0L) : BaseModel