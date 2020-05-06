package com.quantrics.finch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quantrics.finch.global.base.BaseModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RouteModel(

    @Expose
    @SerializedName("route_group_id")
    var routeGroupId: String = "",

    @Expose
    @SerializedName("name")
    var name: String = "",

    @Expose
    @SerializedName("uri")
    var uri: String = "",

    @Expose
    @SerializedName("stop_times")
    var stopTimes: List<StopTimeModel> = ArrayList()) : BaseModel