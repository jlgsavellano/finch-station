package com.quantrics.finch.model

import com.bignerdranch.expandablerecyclerview.model.Parent
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quantrics.finch.global.base.BaseModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StopModel(

    @Expose
    @SerializedName("agency")
    var agency: String = "",

    @Expose
    @SerializedName("name")
    var name: String = "",

    @Expose
    @SerializedName("uri")
    var uri: String = "",

    @Expose
    @SerializedName("routes")
    var routes: List<RouteModel> = ArrayList()) : BaseModel, Parent<RouteModel> {

    override fun getChildList() = routes

    override fun isInitiallyExpanded() = false
}