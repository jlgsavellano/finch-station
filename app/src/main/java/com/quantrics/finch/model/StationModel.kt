package com.quantrics.finch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quantrics.finch.global.base.BaseModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StationModel(

    @Expose
    @SerializedName("stops")
    var stops: List<StopModel> = ArrayList()): BaseModel
